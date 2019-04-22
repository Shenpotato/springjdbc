package com.shenpotato;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Shen_potato on 2019/4/20.
 */
public class JDBCTest {

    private ApplicationContext applicationContext = null;
    private JdbcTemplate jdbcTemplate = null;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = (JdbcTemplate)applicationContext.getBean("jdbcTemplate");
        namedParameterJdbcTemplate =(NamedParameterJdbcTemplate)applicationContext.getBean("namedParameterJdbcTemplate");
    }
    //测试数据源是否连接成功
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource =(DataSource)applicationContext.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testUpdate(){
        String sql = "UPDATE student SET sbirth = ? WHERE sid =? ";
        jdbcTemplate.update(sql,19981122,122);
    }


    //批量操作
    @Test
    public void batchInsert(){
        String sql = "INSERT INTO administor(aid,apassword,alevel) VALUES(?,?,?)";
        List<Object[]> batchargs = new ArrayList<>();
        batchargs.add(new Object[]{"000003","123456","2"});
        batchargs.add(new Object[]{"000004","123456","2"});
        batchargs.add(new Object[]{"000005","123456","2"});
        jdbcTemplate.batchUpdate(sql,batchargs);
    }

    /*
    * 从数据库获取一条记录，实际获取一个对象
    * 应该调用 queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
    * 1.RowMapper指定如何去映射结果集的行，常用的实现类为BeanPropertyRowMapper
    * 2.使用SQL中列的别名完成列名和类的属性名的映射
    * */
    @Test
    public void testqueryforObject(){
        String sql ="SELECT aid, apassword a_password, alevel FROM administor where aid = ?";
        RowMapper<Administor> rowMapper = new BeanPropertyRowMapper<>(Administor.class);
        Administor administor = jdbcTemplate.queryForObject(sql,rowMapper,"000010");
        System.out.println(administor);
    }

    /*
    * 查找类的集合，注意使用query即可
    * */
    @Test
    public void testQueryforList(){
        String sql ="SELECT aid, apassword a_password, alevel FROM administor where aid != ?";
        RowMapper<Administor> rowMapper = new BeanPropertyRowMapper<>(Administor.class);
        List<Administor> administorList = jdbcTemplate.query(sql,rowMapper,"000004");
        System.out.println(administorList);
    }
    /*
    *可以为参数起名字，
    * 1.好处：若有多个参数，则不用对位子，直接对应参数名，便于维护
    * 2.缺点：较为麻烦
    * */
    @Test
    public void testNamedParameterJdbcTemplate(){
        String sql = "INSERT INTO administor(aid,apassword,alevel) VALUES(:aid,:apassword,:alevel)";
        Map<String,Object> paramMap= new HashMap<>();
        paramMap.put("aid","000010");
        paramMap.put("apassword","123456");
        paramMap.put("alevel","3");
        namedParameterJdbcTemplate.update(sql,paramMap);
    }
    /*
    * 使用具名参数时，可以使用update(String sql, SqlParameterSource paramSource)方法进行更新操作
    * 1.sql语句中的参数名和类的属性一致
    * 2.使用SqlParameterSource 的 BeanPropertySqlParameterSource实现类作为参数
    * */
    @Test
    public void testNamedParameterJdbcTemplate2(){
        String sql = "INSERT INTO administor(aid,apassword,alevel) VALUES(:aid,:a_password,:alevel)";
        Administor administor = new Administor();
        administor.setAid("000008");
        administor.setA_password("123456");
        administor.setAlevel("4");
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(administor);
        namedParameterJdbcTemplate.update(sql,parameterSource);
    }


}

package com.shenpotato.jdbc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
// * Created by Shen_potato on 2019/4/21.
 * 推荐使用JdbcTemplate作为成员变量的方式使用
 */
@Repository
public class AdministorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Administor get(String aid){
        String sql ="SELECT aid, apassword a_password, alevel FROM administor where aid = ?";
        RowMapper<Administor> rowMapper = new BeanPropertyRowMapper<>(Administor.class);
        Administor administor = jdbcTemplate.queryForObject(sql,rowMapper,aid);
        System.out.println(administor);
        return administor;
    }
}

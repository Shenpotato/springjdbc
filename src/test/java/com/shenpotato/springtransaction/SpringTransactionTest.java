package com.shenpotato.springtransaction;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by Shen_potato on 2019/4/26.
 */
public class SpringTransactionTest {

    private ApplicationContext applicationContext = null;
    private BookShopDao bookShopDao = null;
    private BookShopService bookShopService = null;
    private Cashier cashier = null;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookShopDao = applicationContext.getBean(BookShopDao.class);
        bookShopService = applicationContext.getBean(BookShopService.class);
        cashier = applicationContext.getBean(Cashier.class);
    }

    @Test
    public void testBookShopDaoFindStockByIsbn(){
        System.out.println(bookShopDao.findBookNumByIsbn("1001"));
    }

    @Test
    public void testBookShopDaoFindpriceByIsbn(){
        System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
    }

    @Test
    public void testBookShopDaoUpdateBookStock(){
        bookShopDao.updateBookStock("1001");
    }

    @Test
    public void testBookShopDaoUpdateUserAccount (){
        bookShopDao.updateUseAccount("AA",100);
    }

    @Test
    public void testBookShopService(){
        bookShopService.purchase("AA","1001");
    }

    @Test
    //测试事务传播行为
    public void testTransactionalPropagation(){
        cashier.checkout("AA", Arrays.asList("1001","1002"));
    }
}

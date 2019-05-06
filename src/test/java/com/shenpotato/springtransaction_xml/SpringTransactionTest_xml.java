package com.shenpotato.springtransaction_xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by Shen_potato on 2019/4/26.
 */
public class SpringTransactionTest_xml {

    private ApplicationContext applicationContext = null;
    private com.shenpotato.springtransaction_xml.BookShopDao bookShopDao = null;
    private com.shenpotato.springtransaction_xml.BookShopService bookShopService = null;
    private com.shenpotato.springtransaction_xml.Cashier cashier = null;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext_xml.xml");
        bookShopDao = applicationContext.getBean(com.shenpotato.springtransaction_xml.BookShopDao.class);
        bookShopService = applicationContext.getBean(com.shenpotato.springtransaction_xml.BookShopService.class);
        cashier = applicationContext.getBean(com.shenpotato.springtransaction_xml.Cashier.class);
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

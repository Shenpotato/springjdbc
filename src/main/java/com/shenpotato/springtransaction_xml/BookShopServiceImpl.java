package com.shenpotato.springtransaction_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Shen_potato on 2019/4/29.
 */
public class BookShopServiceImpl implements BookShopService {

    private BookShopDao bookShopDao;

    public BookShopDao getBookShopDao() {
        return bookShopDao;
    }

    public void setBookShopDao(BookShopDao bookShopDao) {
        this.bookShopDao = bookShopDao;
    }

    @Override
    public void purchase(String username, String isbn) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //1.获取输的单价
        int price = bookShopDao.findBookPriceByIsbn(isbn);
        //2.更新书的库存
        bookShopDao.updateBookStock(isbn);
        //3.更新用户余额
        bookShopDao.updateUseAccount(username,price);
    }
}

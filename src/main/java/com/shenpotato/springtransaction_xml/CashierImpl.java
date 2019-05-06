package com.shenpotato.springtransaction_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Shen_potato on 2019/5/1.
 */
public class CashierImpl implements Cashier {


    private BookShopService bookShopService;

    public BookShopService getBookShopService() {
        return bookShopService;
    }

    public void setBookShopService(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }

    @Override
    public void checkout(String username, List<String> isbns) {
        for(String isbn: isbns){
            bookShopService.purchase(username,isbn);
        }
    }
}

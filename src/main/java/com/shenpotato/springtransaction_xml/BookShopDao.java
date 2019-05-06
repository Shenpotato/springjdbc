package com.shenpotato.springtransaction_xml;

/**
 * Created by Shen_potato on 2019/4/26.
 */
public interface BookShopDao {

    //根据书号获取书的数量
    public int findBookNumByIsbn(String isbn);

    //根据书号获取价格
    public  int findBookPriceByIsbn(String isbn);

    //更新书的库存，使书号对应的库存-1
    public void updateBookStock(String isbn);

    //更新用户余额，使username的balance-price
    public void updateUseAccount(String username, int price);
}

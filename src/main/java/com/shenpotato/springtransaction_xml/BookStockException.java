package com.shenpotato.springtransaction_xml;

/**
 * Created by Shen_potato on 2019/4/29.
 */
public class BookStockException extends RuntimeException {
    private String message;
    public BookStockException(String message){
        System.out.println(message);
    }
}

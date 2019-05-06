package com.shenpotato.springtransaction_xml;

/**
 * Created by Shen_potato on 2019/4/29.
 */
public class UserAccountException extends RuntimeException {
    private String message;
    public UserAccountException(String message){
        System.out.println(message);
    }
}

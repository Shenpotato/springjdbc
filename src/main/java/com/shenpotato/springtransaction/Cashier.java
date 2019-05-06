package com.shenpotato.springtransaction;

import java.util.List;

/**
 * Created by Shen_potato on 2019/5/1.
 */
public interface Cashier {

    public void checkout(String username, List<String> isbns);
}

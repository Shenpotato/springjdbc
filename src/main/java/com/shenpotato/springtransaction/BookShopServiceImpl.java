package com.shenpotato.springtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Shen_potato on 2019/4/29.
 */
@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

    //添加事务注解
    //1.使用propagation指定事务的传播行为，即当前事务方法被另一个事务方法调用时如何使用事务
    //  默认为REQUIRED，即使用调用方法的事务
    //  REQUIRES_NEW：事务为自己的事务，调用的事务方法被挂起
    //2.使用isolation
    //3.默认情况下spring的声明式事务对所有的运行时异常进行回滚，也可以通过对应的属性进行设置
    //noRollbackFor,指定某一异常不回滚，通常为默认即可
    //4.使用readOnly指定事务是否为只读，若真的为一个只读取数据库值的方法，应设置ReadOnl帮助数据库引擎进行优化
    //5.使用timeout指定强制回滚之前，事务可以占用的时间
//    @Transactional(propagation= Propagation.REQUIRES_NEW,
//            isolation = Isolation.READ_COMMITTED,
//            noRollbackFor ={UserAccountException.class})
    @Transactional(propagation= Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED,
            readOnly =false,
            timeout = 3)
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

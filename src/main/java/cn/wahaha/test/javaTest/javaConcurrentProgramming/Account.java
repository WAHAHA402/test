package cn.wahaha.test.javaTest.javaConcurrentProgramming;

import lombok.Data;

/**
 * @Description:
 * @Author: zhangrenwei
 * @Date: 2019-04-20 23:09
 */
@Data
public class Account {
    private Object lock;
    private int balance;
    private Account (){
    }
    public Account(Object lock){
        this.lock = lock;
    }

    void transfer(Account target, int amt){
        //锁定转出账户
        synchronized(this) {
            //锁定转入账户
            synchronized(target) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}

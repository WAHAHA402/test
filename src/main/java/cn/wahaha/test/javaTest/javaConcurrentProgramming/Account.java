package cn.wahaha.test.javaTest.javaConcurrentProgramming;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: zhangrenwei
 * @Date: 2019-04-20 23:09
 */
@Data
//public class Account {
//    private Object lock;
//    private int balance;
//    private Account (){
//    }
//    public Account(Object lock){
//        this.lock = lock;
//    }
//
//    void transfer(Account target, int amt){
//        //锁定转出账户，两次获取锁可能会导致死锁问题
//        synchronized(this) {
//            //锁定转入账户
//            synchronized(target) {
//                if (this.balance > amt) {
//                    this.balance -= amt;
//                    target.balance += amt;
//                }
//            }
//        }
//    }
//}


class Allocator {
    private List<Object> als =
            new ArrayList<>();

    // 一次性申请所有资源，破坏占有且等待条件
    synchronized boolean apply(
            Object from, Object to) {
        if (als.contains(from) ||
                als.contains(to)) {
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    // 归还资源
    synchronized void free(
            Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }
}

public class Account {
    // actr应该为单例
    private Allocator actr;
    private int balance;

    // 转账
    void transfer(Account target, int amt){
        // 一次性申请转出账户和转入账户，直到成功
        while (!actr.apply(this, target))
            ;
        try {
            // 锁定转出账户
            synchronized (this) {
                // 锁定转入账户
                synchronized (target) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actr.free(this, target);
        }
    }

    private final AtomicInteger atomicInteger = new AtomicInteger(2);

    public static void main(String[] args) {
        System.out.println(new Account().atomicInteger.addAndGet(2));
    }

}


class Account2 {
    private int id;
    private int balance;

    // 转账
    void transfer(Account2 target, int amt) {
        //按照某一值(这里是id)来排序获取锁资源，这是最简单的避免死锁的获取锁的方式
        Account2 left = this;
        Account2 right = target;
        if (this.id > target.id) {
            left = target;
            right = this;
        }

        // 锁定序号小的账户
        synchronized (left) {
            // 锁定序号大的账户
            synchronized (right) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}

package cn.wahaha.test.javaTest.javaConcurrentProgramming.thread;

import cn.wahaha.test.javaTest.javaConcurrentProgramming.Account;

/**
 * @Description: 线程属性
 * @Author: zhangrenwei
 * @Date: 2019-05-04 22:37
 */

public class ThreadAttribution {
    Runnable runnable = () -> {
        //打印当前线程的ThreadGroup的名字， 发现结果为main
        System.out.println(Thread.currentThread().getThreadGroup().getName());

//        Thread.currentThread().getThreadGroup(){
//            //todo 这里可以重写ThreadGroup的uncaughtException方法 具体可看该方法的源码，其制定了异常处理方式
//          uncaughtException()
//        };
        Account account = null;
        System.out.println(account.getBalance());
    };

    public static void main(String[] args) {
        ThreadAttribution attribution = new ThreadAttribution();
        Thread thread = new Thread(attribution.runnable);
        //这是一个测试未捕获异常处理器的线程～～～
        thread.setName("call me thread007");

        UncaughtExceptionHandlerTest test = new UncaughtExceptionHandlerTest();
        //为thread设置异常处理器，不设置其处理器默认为其ThreadGroup对象
        //thread.setUncaughtExceptionHandler(test);
        thread.start();
    }

}

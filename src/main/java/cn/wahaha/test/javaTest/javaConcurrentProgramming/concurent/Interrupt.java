package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: java核心技术14章 14.2中断线程
 * @Author: zhangrenwei
 * @Date: 2019-05-04 13:40
 */

public class Interrupt {
    Runnable runnable = () -> {
      try {
          while(!Thread.currentThread().isInterrupted()) {
              System.out.println("开始了");
              Thread.sleep(2000);

              //此处会得到RUNNABLE的状态，而不是TIMED_WAITING，是执行完两秒之后回到runnable状态了吗
              // 操作系统层面的线程状态和jvm的线程的状态是不一样的 但是这么说好像有点问题
              System.out.println(Thread.currentThread().getState());
              System.out.println("第n次");


              Thread.currentThread().interrupt();
              System.out.println(Thread.currentThread().getState());
              System.out.println(Thread.currentThread().isInterrupted());
              System.out.println("第n2次");

              Thread.sleep(2000);
              System.out.println(Thread.currentThread().getState());
              System.out.println("第n3次");
          }
      }
      catch (InterruptedException e) {
          //调用interrupt之后，再调用sleep会报InterruptedException错，且会清除中断状态。
          //sleep join wait等方法都会跑出InterruptedException异常，这个异常是因为调用interrupt方法导致的
          System.out.println("very well, it caused a InterruptedException");
      }
//      catch (Exception e) {
//          System.out.println("exception");
//      }
//      finally {
//
//      }
    };

    public static void main(String[] args) throws InterruptedException {
//        Interrupt i = new Interrupt();
//        Thread thread = new Thread(i.runnable);
//        thread.start();
        Lock lock = new ReentrantLock(true);
    }
}

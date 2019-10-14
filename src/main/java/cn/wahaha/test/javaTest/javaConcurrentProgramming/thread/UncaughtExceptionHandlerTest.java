package cn.wahaha.test.javaTest.javaConcurrentProgramming.thread;

/**
 * @Description:
 * @Author: zhangrenwei
 * @Date: 2019-05-04 22:39
 */

public class UncaughtExceptionHandlerTest implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("获取产生非受查异常的线程的名字： \n" + t.getName());
        System.out.println("异常信息： " + e.getMessage());
    }
}

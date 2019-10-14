package cn.wahaha.test.javaTest.javaConcurrentProgramming;

/**
 * @Description:
 * @Author: zhangrenwei
 * @Date: 2019-03-27 10:57
 */

public class FirstTest {

    private long count = 0;
     void add10k(){
        int id = 0;
        while (id++ < 100000000) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final FirstTest firstTest = new FirstTest();
        //创建两个线程，执行add操作
        Thread thread1 = new Thread(() -> firstTest.add10k());
        Thread thread2 = new Thread(() -> {
            firstTest.add10k(); 
        });
//        thread1.setPriority(10);
//        thread2.setPriority(1);
        //启动两线程
        thread1.start();

        thread2.start();
        //等待俩线程结束,join方法使得当前线程结束，加进来的线程执行完毕
        thread1.join();
        thread2.join();
        System.out.println(thread1.getThreadGroup());
        System.out.println(firstTest.count);
    }

}

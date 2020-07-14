package cn.wahaha.test.javaTest.javaConcurrentProgramming;

/**
 * @Description:
 * @Author: zhangrenwei
 * @Date: 2019-03-27 10:57
 */

public class FirstTest {

    private volatile long count = 0; //加入volatile仍然得不到正确结果，保证可见性，不保证原子性,可通过给count++这行代码加锁保证原子性
     void add10k(){
        int id = 0;
         while (id++ < 10000) {
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
//        thread1.join(); 将下面的thread1.join放在这里,则会得到20000的结果
        thread2.start();
        //等待俩线程结束,join方法使得当前线程结束，加进来的线程执行完毕
        thread1.join();
        thread2.join();
        System.out.println(firstTest.count);
    }

}

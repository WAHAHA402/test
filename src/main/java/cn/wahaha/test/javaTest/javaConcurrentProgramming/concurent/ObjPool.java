package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Description: ObjPool 用Java并发包的信号量(Semaphore)实现一个简单的限流器
 * @Author: zhangrenwei
 * @Date: 2019-10-04 23:21
 */
/*
    我理解的和管程相比，信号量可以实现的独特功能就是同时允许多个线程进入临界区，但是信号量不能做的就是同时唤醒多个线程去争抢锁，
    只能唤醒一个阻塞中的线程，而且信号量模型是没有Condition的概念的，即阻塞线程被醒了直接就运行了而不会去检查此时临界条件是否已经不满足了，
    基于此考虑信号量模型才会设计出只能让一个线程被唤醒，否则就会出现因为缺少Condition检查而带来的线程安全问题。正因为缺失了Condition，
    所以用信号量来实现阻塞队列就很麻烦，因为要自己实现类似Condition的逻辑。

 */
public class ObjPool<T, R> {
    //池资源
    final List<T> pool;

    final Semaphore semaphore;

    ObjPool(int size, T t) {
        //这里使用Vector，因为信号量支持多个线程进入临界区，执行add和remove方法可能产生并发问题
        pool = new Vector<T>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        semaphore = new Semaphore(size);
    }

    void exec(Function<T, R> function) {
        T t = null;

        try {
            semaphore.acquire();
            t = pool.remove(0);
            function.apply(t);
        } catch (InterruptedException e){

        } finally {
            pool.add(t);
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        //创建对象池
        ObjPool<Long, Integer> objPool =
                new ObjPool<Long, Integer>(5, (long) new Random().nextInt());
        Function function = t -> {
            System.out.println(t);
            try {
                //睡眠三秒
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(t.toString());
        };

        for (int i = 0; i < 10; i++) {
            //池容量设为5，建立十个线程，睡眠三秒，前五个立即执行打印，后五个等空余出池资源之后才会执行打印
            new Thread(() -> objPool.exec(function)).start();
        }

    }

}

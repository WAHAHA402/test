package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: BlockedQueue 利用Java并发包里的Condition（条件变量，2个）来快速实现阻塞队列
 * 一个阻塞队列，需要两个条件变量，一个是队列不空（空队列不允许出队），一个是队列不满（队列已满不允许入队）
 * @Author: zhangrenwei
 * @Date: 2021/1/26 4:11 下午
 */

/**
 * 需要注意，Lock 和 Condition 实现的管程，线程等待和通知需要调用 await()、signal()、signalAll()，它们的语义
 * 和 wait()、notify()、notifyAll() 是相同的。但是不一样的是，Lock&Condition 实现的管程里只能使用前面的 await()、signal()、
 * signalAll()，而后面的 wait()、notify()、notifyAll() 只有在 synchronized 实现的管程里才能使用。
 * 如果一不小心在 Lock&Condition 实现的管程里调用了 wait()、notify()、notifyAll()，那程序可就彻底玩儿完了。
 */
public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock();

    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();

    // 条件变量，队列不空
    final Condition notEmpty = lock.newCondition();

    // 入队方法
    void enq(T t) {
        lock.lock();
        try {
            // 下面伪代码会报错，故而注释
//            while (队列已满){
//
//                notFull.await();
//            }
//            // 省略入队操作
//            // 入队以后，通知其他线程可以出队
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // 出队方法
    void deq() {
        lock.lock();
        try {
            // 下面伪代码会报错，故而注释
//            while (队列已空) {
//                // 需要捕获异常
//                notEmpty.await();
//            }
//            // 省略出队操作
//            // 出队以后，通知其他线程可以入队
//            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }


}

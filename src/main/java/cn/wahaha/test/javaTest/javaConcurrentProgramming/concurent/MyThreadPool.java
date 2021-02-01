package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: MyThreadPool
 * @Author: zhangrenwei
 * @Date: 2021/2/1 7:30 下午
 */

/**
 * 在 MyThreadPool 的内部，我们维护了一个阻塞队列 workQueue 和一组工作线程，工作线程的个数由构造函数中的 poolSize 来指定。
 * 用户通过调用 execute() 方法来提交 Runnable 任务，execute() 方法的内部实现仅仅是将任务加入到 workQueue 中。MyThreadPool 内部维护的
 * 工作线程会消费 workQueue 中的任务并执行任务，相关的代码就是下面的 while 循环。线程池主要的工作原理就这些，是不是还挺简单的？
 */
// 简化的线程池，用来说明工作原理
public class MyThreadPool {
    // 利用阻塞队列实现生产者-消费者模型
    BlockingQueue<Runnable> workQueue;

    List<WorkerThread> threads = new ArrayList<>();

    // 构造方法
    MyThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;

        //创建工作线程
        for (int i = 0; i < poolSize; i++) {
            WorkerThread worker = new WorkerThread();
            worker.setName("================= new Thread :" + (i + 1));
            System.out.println(worker.getName());

            worker.start();
            threads.add(worker);
        }
    }

    void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);
    }


    class WorkerThread extends Thread {
        public void run() {
            // 循环取任务并执行
            while (true) {
                Runnable task = null;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }

        }
    }

    // 使用例子
    public static void main(String[] args) throws InterruptedException {
        // 创建有界阻塞队列
        BlockingQueue queue = new LinkedBlockingQueue(2);
        MyThreadPool pool = new MyThreadPool(10, queue);

        pool.execute(() -> {
            System.out.println("this is a task");
        });
    }

}

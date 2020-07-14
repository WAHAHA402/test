package cn.wahaha.test.javaTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: MyThreadPool, 现在线程池的设计普遍采用生产者-消费者模式，线程池使用方为生产者，线程池为消费者；
 * 下面是一个简单线程池实现及其使用demo；
 * @Author: zhangrenwei
 * @Date: 2019-10-19 23:19
 */
//简化的线程池，仅用来说明工作原理
public class MyThreadPool {
    //利用阻塞队列来实现生产者-消费者模式
    BlockingQueue<Runnable> workQueue;

    //List<MyThread> workThreads = new ArrayList<>();

    public MyThreadPool(int size, BlockingQueue workQueue) {
        this.workQueue = workQueue;
        for (int i = 0; i < size; i++) {
            MyThread myThread = new MyThread();
            //这里需要提前开启线程，试图在execute方法中开启全部线程，任务不会执行
            myThread.start();
            //workThreads.add(myThread);
        }
    }

    //提交任务
    public void execute(Runnable task) throws InterruptedException {
        workQueue.put(task);
    }

    //工作线程负责消费任务，并执行任务
    class MyThread extends Thread {
        public void run() {
            //循环取任务并执行
            while (true) {
                try {
                    Runnable task = workQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue workQueue = new LinkedBlockingQueue(4);
        MyThreadPool myThreadPool = new MyThreadPool(5, workQueue);

        for (int i = 0; i < 100; i++) {
            int times = ++i;
            myThreadPool.execute(() -> {
                System.out.println("这是第" + times + "次");
            });
        }
    }

}

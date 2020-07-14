package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Description: ForkJoinInJava
 * @Author: zhangrenwei
 * @Date: 2020/6/22 2:34 下午
 */

public class ForkJoinInJava {
    public static void main(String[] args) {
        //创建分治任务线程池 parallelism:并行（这里应该是指并行任务的数量）
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        //创建分治任务
        Fibonacci fibonacci = new Fibonacci(10);

        //启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);

        //输出结果
        System.out.println(result);
    }

    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            //创建子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);

            //等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }
}

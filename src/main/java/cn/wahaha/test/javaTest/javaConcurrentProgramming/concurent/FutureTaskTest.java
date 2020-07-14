package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.concurrent.*;

/**
 * @Description: FutureTaskTest
 * @Author: zhangrenwei
 * @Date: 2020/5/28 7:25 下午
 */

public class FutureTaskTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        FutureTask<String> futureTask2 = new FutureTask<>(new Task2());
        FutureTask<String> futureTask1 = new FutureTask<>(new Task1(futureTask2));

        executorService.execute(futureTask1);

//        TimeUnit.SECONDS.sleep(3); //无用
//        System.out.println("堵你小伙3秒");

        executorService.execute(futureTask2);

    }


    static class Task1 implements Callable<String> {
        private FutureTask<String> futureTask2;

        Task1(FutureTask<String> futureTask2) {
            this.futureTask2 = futureTask2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1: 洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1: 烧开水...");
            TimeUnit.SECONDS.sleep(1);

            String tea = futureTask2.get();
            System.out.println("T1: 拿到茶叶 " + tea + " ...");

            System.out.println("T1: 泡茶...");
            return "上茶:" + tea;
        }


    }

    static class Task2 implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("T2: 洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2: 洗茶杯...");
            TimeUnit.MILLISECONDS.sleep(1000);

            System.out.println("T2: 拿茶叶");
            TimeUnit.SECONDS.sleep(1);
            return "龙井";
        }
    }


}

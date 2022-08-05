package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: CompletableFuture Test
 * @Author: zhangrenwei
 * @Date: 2020/6/15 1:43 下午
 */
//todo 默认情况下 CompletableFuture 使用的几个静态方法公用一个默认的线程池，不可取，一般要视情况，使用同名的带有线程池参数的方法，以避免互相干扰！！！
public class CompletableFutureTest {
    public static void sleepMilliSeconds(int t) {
        try {
            TimeUnit.MILLISECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        chuanHangGuanXi();
//        huiJuGuanXi_AND();
//        huiJuGuanXi_OR();
        handleException();

    }


    public static void chuanHangGuanXi() {
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶壶。。。");
            sleepMilliSeconds(1000);

            System.out.println("T2: 洗茶杯。。。");
            sleepMilliSeconds(1000);

            System.out.println("T2: 拿茶叶。。。");
            sleepMilliSeconds(1000);
            return "龙井";
        });

        //串行关系
        CompletableFuture<Void> f4 = f2.thenApply(tea -> {
            sleepMilliSeconds(1000);
            System.out.println("f4 thenApply: " + tea);
            return tea;
        }).thenAccept(tea -> {
            sleepMilliSeconds(1000);
            System.out.println("f4 thenAccept: " + tea);
        }).thenRun(() -> {
            sleepMilliSeconds(1000);
            System.out.println("f4 thenRun");
        });

        f4.join();
    }

    //And汇聚关系
    public static void huiJuGuanXi_AND() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            sleepMilliSeconds(1000);
            System.out.println("T1： 洗水壶。。。");

            sleepMilliSeconds(5000);
            System.out.println("T1： 烧开水。。。");
            return "这是烧好的开水";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            sleepMilliSeconds(1000);
            System.out.println("T2: 洗茶壶。。。");

            sleepMilliSeconds(1000);
            System.out.println("T2: 洗茶杯。。。");

            sleepMilliSeconds(1000);
            System.out.println("T2: 拿茶叶。。。");
            return "龙井";
        });

        CompletableFuture<String> f3 = f1.thenCombine(f2, ((a, s) -> {

            System.out.println("T3: 拿到茶叶：" + s);

            //这里a是f1的返回值，s是f2的返回值
            System.out.println("T3: 泡茶。。。" + a);
            return "上茶：" + s;
        }));
        System.out.println(f3.join());
    }

    //Or汇聚关系
    public static void huiJuGuanXi_OR() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            int i = new Random().nextInt(8);
            sleepMilliSeconds(i * 1000);
            return "f1: " + i;
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            int i = new Random().nextInt(8);
            sleepMilliSeconds(i * 1000);
            return "f2: " + i;
        });
        CompletableFuture<String> f3 = f1.applyToEither(f2, f1OrF2ResultString -> f1OrF2ResultString);
//        CompletableFuture<Void> f3 = f1.acceptEither(f2, System.out::println);
//        CompletableFuture<Void> f3 = f1.runAfterEither(f2, () -> {
//            System.out.println("runnable without arguments");
//        });



        System.out.println(f3.join());
    }

    public static void handleException() {
        CompletableFuture<Integer> future0 = CompletableFuture
                .supplyAsync(() -> 7/14)
                // 拿到上一步结果或异常信息，不抛出异常
                .whenComplete((resultValueFromLastStep, thisIsException) -> {
                    sleepMilliSeconds(0);
                    System.out.println("7/14 will not go wrong, result is \"" + resultValueFromLastStep + "\", exception is " + thisIsException);
                });

        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(() -> 7/0)
                // 拿到上一步结果或异常信息，不抛出异常
                .whenComplete((resultValueFromLastStep, thisIsException) -> {
                    sleepMilliSeconds(500);
                    System.out.println("7/0 will go wrong, result is \"" + resultValueFromLastStep + "\", exception is \"" + thisIsException + "\"");
                });

        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(() -> 7/0)
                // 当异常发生时，返回新的结果，不抛出异常
                .exceptionally(exception -> {
                    sleepMilliSeconds(1000);
                    int newResult = 1;
                    System.out.println("7/0 will go wrong, exception is \"" + exception + "\", so we use \"" + newResult + "\" to replace");
                    return newResult;
                });

        CompletableFuture<Integer> future3 = CompletableFuture
                .supplyAsync(() -> 7/0)
                // 拿到返回的结果和异常信息（两者一般只会存在一个非空），返回函数执行后的结果
                .handle((resultValueFromLastStep, thisIsException) -> {
                    sleepMilliSeconds(1500);
                    int newResult = 1000;
                    System.out.println("7/0 will go wrong, result is \"" + resultValueFromLastStep + "\", exception is \"" + thisIsException + "\"" + ", so we use \"" + newResult + "\" to replace");
                    return newResult;
                });

        }





}

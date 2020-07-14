package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: CompletionService_ForkingCluster
 * @Author: zhangrenwei
 * @Date: 2019-10-28 19:55
 */
//Dobbo中有一种叫做Forking的集群模式，支持并行地调用多个查询服务，只要有一个成功返回结果，整个服务就可以返回了。这里用CompletionService来实现一个

public class CompletionService_ForkingCluster {
    public static void main(String[] args) {
        CompletionService_ForkingCluster csfc = new CompletionService_ForkingCluster();
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //创建CompletionService
        CompletionService<Integer> completionService = new ExecutorCompletionService(executorService);

        //用来存储Future
        List<Future<Integer>> futures = new ArrayList<>(3);
        futures.add(completionService.submit(() -> csfc.getInfoFromChannel1()));
        futures.add(completionService.submit(() -> csfc.getInfoFromChannel2()));
        futures.add(completionService.submit(() -> csfc.getInfoFromChannel3()));

        //获取最快返回的任务结果
        Integer r = null;
        for (int i = 0; i < 3; i++) {
            try {
                r = completionService.take().get();
                //简单通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                //取消所有任务
                for (Future<Integer> f : futures) {
                    f.cancel(true);
                }
            }
        }
        //返回结果
        System.out.println(r);
    }

    public Integer getInfoFromChannel1() {
        try {
            //取消该任务时，若为执行结束，会产生InterruptedException异常
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("=============================================");
            System.out.println("this exception comes from getInfoFromChannel1");
            System.out.println("=============================================");
        }
        return 1;
    }

    public Integer getInfoFromChannel2() {
        return 2;
    }

    public Integer getInfoFromChannel3() {
        return 3;
    }

}

package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Description: ObjPool 用Java并发包的信号量(Semaphore)实现一个简单的限流器
 * @Author: zhangrenwei
 * @Date: 2019-10-04 23:21
 */

public class ObjPool<T, R> {
    final List<T> pool;

    final Semaphore semaphore;

    ObjPool(int size, T t) {
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
        ObjPool<Long, String> objPool =
                new ObjPool<Long, String>(10, 2L);
        objPool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });
    }

}

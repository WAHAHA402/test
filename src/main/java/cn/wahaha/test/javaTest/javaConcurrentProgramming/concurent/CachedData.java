package cn.wahaha.test.javaTest.javaConcurrentProgramming.concurent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁：降级demo； ps：升级是不允许的， 这也是官方demo
 * @Author: zhangrenwei
 * @Date: 2019-10-08 16:27
 */

public class CachedData {
    Object data;
    volatile boolean cacheValid;
    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读锁
    final Lock r = readWriteLock.readLock();
    //写锁
    final Lock w = readWriteLock.writeLock();

    void processCachedData() {
        //获取读锁
        r.lock();
        if (!cacheValid) {
            //因为不支持锁的升级，所以这里需要先释放读锁
            r.unlock();
            //再获取写锁
            w.lock();
            try{
                //高并发下，可能存在其他线程已经更新数据，所以此处需要再次验证，避免重复更新数据
                if (!cacheValid){
                    //data = ...
                    cacheValid = true;
                }
                //降级为读锁
                r.lock();
            } finally {
                //释放写锁，此时读锁仍然持有，为了后续处理数据的正确性
                w.unlock();
            }
        }
        try{
            //use(data);
        } finally {
            //读锁释放
            r.unlock();
        }

    }

}

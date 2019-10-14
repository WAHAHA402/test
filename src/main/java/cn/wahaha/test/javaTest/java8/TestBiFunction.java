package cn.wahaha.test.javaTest.java8;

import java.util.function.BiFunction;

/**
 * @Author: zhangrenwei
 * @Date: 2019-03-14 19:50
 */

public class TestBiFunction {
    public static String doSomething(int n, int m, BiFunction<Integer, Integer, String> bf){
        return (bf.apply(n, m));
    }

    public static void main(String[] args) {
        String zz = doSomething(10, 9, (Integer x, Integer y) -> {
            int z = x + y;
            System.out.println(String.valueOf(z));
            return String.valueOf(z);
        });
        System.out.println(zz);
    }
}

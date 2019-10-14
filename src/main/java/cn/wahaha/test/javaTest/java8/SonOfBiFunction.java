package cn.wahaha.test.javaTest.java8;

import java.util.function.BiFunction;

/**
 * @Author: zhangrenwei
 * @Date: 2019-03-14 19:41
 */
//BiFunction是个函数式接口，前面两个参数是输入，第三个参数是返回值
    //这个类写在这里没啥意义，BiFunction想用还是能用
@FunctionalInterface
public interface SonOfBiFunction extends BiFunction<Integer, Integer, String> {
    @Override
    public String apply(Integer integer, Integer integer2);

}

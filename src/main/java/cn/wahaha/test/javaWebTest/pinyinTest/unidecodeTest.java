package cn.wahaha.test.javaWebTest.pinyinTest;

import me.xuender.unidecode.Unidecode;

/**
 * @Description: unidecodeTest
 * @Author: zhangrenwei
 * @Date: 2019-07-02 11:28
 */

public class unidecodeTest {
    public static void main(String[] args) {
        System.out.println(Unidecode.decode("音乐， 声调， 子弹， 处方"));
        System.out.println(Unidecode.decode("≈这是一条shenqi的天8路o!"));
    }
}

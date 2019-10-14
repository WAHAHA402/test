package cn.wahaha.test.javaTest;

/**
 * @description:
 * @author: zhangrenwei
 * @create: 2019-01-04
 **/

public class tempTest {
    public static void main(String[] args) {
        /**
         * 参考 https://blog.csdn.net/lonely_fireworks/article/details/7962171
         */
        //0 补0
        String format = "%0" + 3 + "d";
        System.out.println(String.format(format, 1));

        //补括号包含负数
        System.out.println(String.format("%(f", -99.9));
        //补逗号分割数字
        System.out.println(String.format("%,f", 999999.9));
        //+ 为正数或者负数添加符号
        System.out.println(String.format("%+d", -15));
        //- 左对齐,效果： |9  |
        System.out.println(String.format("%-3d", 9));

        System.out.println(String.format("%06.2f", 9.349));

    }
}

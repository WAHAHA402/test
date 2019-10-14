package cn.wahaha.test.javaTest.java8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Author: zhangrenwei
 * @Date: 2019-03-14 19:12
 */

public class TimerLambda {
    public static void repeatMessage(String text, int delay){
        ActionListener listener = e -> {
            System.out.println(text);

            Toolkit.getDefaultToolkit().beep();
        };
        new Timer(delay, listener).start();
    }

    public static void main(String[] args) {
        TimerLambda.repeatMessage("你好", 1000);
        
    }
}

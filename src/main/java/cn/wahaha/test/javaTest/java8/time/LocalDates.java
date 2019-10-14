package cn.wahaha.test.javaTest.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: LocalDates
 * @Author: zhangrenwei
 * @Date: 2019-06-17 13:46
 */
//java8 本地日期类LocalDate
public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate alonzosBirthday = LocalDate.of(1903, Month.JUNE, 4);
        System.out.println(alonzosBirthday);

        //程序员日是每年的第256天
        LocalDate programmerDay = LocalDate.of(2019, Month.JANUARY, 1).plusDays(255);
        System.out.println(programmerDay);

        //18年独立日和圣诞节差的时间
        LocalDate independenceDay = LocalDate.of(2018, Month.JULY, 4);
        LocalDate christmas = LocalDate.of(2018, Month.DECEMBER, 25);
        System.out.println("独立日和圣诞节的时间差" + christmas.until(independenceDay));
        //chronoUnit 计时单位
        System.out.println("独立日和圣诞节的时间差" + christmas.until(independenceDay, ChronoUnit.DAYS));

        LocalDate thirdMondayInJune = LocalDate.of(2019, 6, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY));
        System.out.println("今年六月份的第三个周一: " + thirdMondayInJune);
    }

}

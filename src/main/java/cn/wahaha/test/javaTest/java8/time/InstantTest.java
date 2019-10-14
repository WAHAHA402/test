package cn.wahaha.test.javaTest.java8.time;

import java.sql.SQLOutput;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Description: InstantTest
 * @Author: zhangrenwei
 * @Date: 2019-06-20 14:14
 */

public class InstantTest {
    public static void main(String[] args) {
        Instant instant = new Date().toInstant();
        Date date = Date.from(LocalDateTime.of(1997, 10, 14, 02, 14).toInstant(ZoneOffset.UTC));
        Date date1 = java.sql.Date.valueOf(LocalDate.of(1997, 10, 14));

        System.out.println(instant);
        System.out.println(date);
        System.out.println(date1);

        LocalDateTime localDate1 = LocalDateTime.of(1997, Month.OCTOBER, 17, 10, 12);
        LocalDateTime localDate2 = LocalDateTime.of(1997, 10, 12, 10, 12);
        //between方法的参数，必须支持秒单位(可查看java doc)，支持LocalTime、LocalDateTime等，不支持LocalDate
        Duration duration1 = Duration.between(localDate2, localDate1);
        System.out.println(duration1.toDays());
        System.out.println(duration1.toHours());
        System.out.println(duration1.toMinutes());
        System.out.println(duration1.toMillis());

        //Period的between方法只支持LocalDate
        Period period = Period.between(
                LocalDate.of(2019, 06, 20),
                LocalDate.of(2019, 06, 15));
        Period period1 = Period.ofDays(5);
        System.out.println("毕业已经" + period.get(ChronoUnit.DAYS) + "天了。");
        System.out.println("毕业已经" + period.getDays() + "天了。");
        System.out.println("这个方法返回的是？ " + period.getChronology() + "。");
        System.out.println("毕业已经" + period.getMonths() + "月了。");

    }
}

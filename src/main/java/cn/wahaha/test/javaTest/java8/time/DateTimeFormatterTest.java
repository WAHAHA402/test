package cn.wahaha.test.javaTest.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @Description: DateTimeFormatterTest
 * @Author: zhangrenwei
 * @Date: 2019-06-20 10:53
 */

public class DateTimeFormatterTest {
    public static void main(String[] args) {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 50, 0,
                ZoneId.of("Asia/Shanghai"));
        System.out.println(apollo11launch);

        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apollo11launch);
        System.out.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG);
        System.out.println(formatter.format(apollo11launch));
        System.out.println(formatter1.format(apollo11launch));
        System.out.println(formatter2.format(apollo11launch));
        System.out.println(formatter3.format(apollo11launch));

        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("E a yyyy-MM-dd HH:mm");
        System.out.println(formatter4.format(apollo11launch));

        //这里默认formatter格式为 yyyy-MM-dd
        LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
        System.out.println(churchsBirthday);
        LocalDateTime churchsBirthDay2 = LocalDateTime.parse("1903-06-14 09:03:33", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(churchsBirthDay2);

        for (DayOfWeek w : DayOfWeek.values()) {
            System.out.println(w.getDisplayName(TextStyle.FULL, Locale.CHINESE) + "");
        }

    }


}

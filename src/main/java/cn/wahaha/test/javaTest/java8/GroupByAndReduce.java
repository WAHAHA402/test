package cn.wahaha.test.javaTest.java8;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 流的群组和分区
 * @Author: zhangrenwei
 * @Date: 2019-03-15 16:02
 */
public class GroupByAndReduce {

    public static void main(String[] args) {
        /**
        Stream<Locale> locale = Stream.of(Locale.getAvailableLocales());
        locale.forEach((Locale l) -> System.out.println(l + " "));

        locale = Stream.of(Locale.getAvailableLocales());
        Map<String, List<Locale>> countryToLocales = locale.collect(
                Collectors.groupingBy(Locale::getCountry)
        );
        System.out.println(countryToLocales);
         */

        Stream<Locale> locale = Stream.of(Locale.getAvailableLocales());
        Map<Boolean, List<Locale>> englishAndOtherLocales = locale.collect(
                Collectors.partitioningBy(locale1 -> locale1.getLanguage().equals("en")));
        List<Locale> locales = englishAndOtherLocales.get(true);
        System.out.println(locales);
        System.out.println(englishAndOtherLocales.get(false));
        //System.out.println(HexUtils.getHex(105));
    }

}


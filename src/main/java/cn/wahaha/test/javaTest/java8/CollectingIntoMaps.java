package cn.wahaha.test.javaTest.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhangrenwei
 * @Date: 2019-03-15 14:54
 */

public class CollectingIntoMaps {

    public static class Person{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return getClass().getName() +
                    "{id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static Stream<Person> people(){
        return Stream.of(new Person(1, "Peter"), new Person(2, "Bob"),
                new Person(3, "Mary"));
    }

    public static void main(String[] args) {
//        Map<Integer, String> idToName = people().collect(
//                Collectors.toMap(Person::getId, Person::getName));
//        System.out.println("idToName: " + idToName);
//
//        Map<Integer, Person> idToPerson = people().collect(
//                Collectors.toMap(Person::getId, Function.identity())
//        );
//        System.out.println("idToPerson: " +  idToPerson.getClass().getName() + idToPerson);
//
//        idToPerson = people().collect(
//                Collectors.toMap(Person::getId, Function.identity(),(
//                        existingValue, newValue) -> {
//                    throw new IllegalStateException();
//                }, TreeMap::new));
//        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(
                Collectors.toMap(
                        Locale::getDisplayLanguage,
                        l -> l.getDisplayLanguage(l),
                        (existingValue, newValue) -> existingValue));
        System.out.println("languageNames: " + languageNames);

        //流用完即自动关闭，不可重复使用，所以这里重新赋值
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> coutryLanguageSets = locales.collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Collections.singleton(l.getDisplayLanguage()),
                        (a, b) -> { //union of a and b
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }));
        System.out.println("coutryLanguageSets: " + coutryLanguageSets);
    }







}

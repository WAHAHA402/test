package cn.wahaha.test.javaTest.javaTest;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @Description: CollectionsTest
 * @Author: zhangrenwei
 * @Date: 2019-10-09 15:03
 */

public class CollectionsTest {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(1, "yo");
        map.put(1, "bro");
        Iterator iterator = map.values().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Iterator iterator1 = map.entrySet().iterator();
        while(iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }
}

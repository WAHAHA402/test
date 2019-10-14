package cn.wahaha.test.javaWebTest.pinyinTest;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @Description: pinyinTest
 * @Author: zhangrenwei
 * @Date: 2019-07-02 10:43
 */

public class JpinyinTest {
    public static void main(String[] args) throws PinyinException, FileNotFoundException {
        StringBuffer buffer = new StringBuffer();
        //叾 厼 哛 唜
        buffer.append("猄猅猆猇猈猉猊猋猌猍猑").append("zheshi12来者不拒8是i");
        long time1 = System.currentTimeMillis();
        System.out.println(PinyinHelper.convertToPinyinString(buffer.toString(), "", PinyinFormat.WITHOUT_TONE));
        System.out.println(System.currentTimeMillis() - time1);

        System.out.println(PinyinHelper.convertToPinyinString("声调，音乐", "", PinyinFormat.WITHOUT_TONE));
        System.out.println(PinyinHelper.convertToPinyinString("声调，音乐", "", PinyinFormat.WITH_TONE_NUMBER));
        PinyinHelper.addMutilPinyinDict("/Users/zhangrenwei/IdeaProjects/test1/src/main/resources/multi.dict");
        System.out.println(PinyinHelper.convertToPinyinString("声调，音乐", ",", PinyinFormat.WITH_TONE_NUMBER));


        /**
         * 获取某个中文字符可能的发音，如 为可以有两种发音[wèi, wéi]
         */
        String[] weiArray = PinyinHelper.convertToPinyinArray('为');
        // [wèi, wéi]
        System.out.println(Arrays.toString(weiArray));

        /**
         * 沒有音调
         */
        String[] weiArrayWithoutTone = PinyinHelper.convertToPinyinArray('为',
                PinyinFormat.WITHOUT_TONE);
        //[wei]
        System.out.println(Arrays.toString(weiArrayWithoutTone));

        /**
         * 有音调，使用数字表示音调
         */
        String[] weiArrayWithoutToneNum= PinyinHelper.convertToPinyinArray('为',
                PinyinFormat.WITH_TONE_NUMBER);
        //[wei4, wei2]
        System.out.println(Arrays.toString(weiArrayWithoutToneNum));

        /**
         * 获取字符串对应拼音的首字母
         */
        try {
            String shortPinyin = PinyinHelper.getShortPinyin("我爱杭州");
            //wahz
            System.out.println(shortPinyin);
        } catch (PinyinException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /**
         * 将字符串转换成相应格式的拼音
         */
        try {
            String pinyinString = PinyinHelper.convertToPinyinString("杭州西湖", ",", PinyinFormat.WITH_TONE_MARK);
            //háng,zhōu,xī,hú
            System.out.println(pinyinString);
        } catch (PinyinException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            String pinyinString = PinyinHelper.convertToPinyinString("杭州西湖", "", PinyinFormat.WITHOUT_TONE);
            //hang,zhou,xi,hu
            System.out.println(pinyinString);
        } catch (PinyinException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            String pinyinString = PinyinHelper.convertToPinyinString("杭州西湖", ",", PinyinFormat.WITH_TONE_NUMBER);
            //hang2,zhou1,xi1,hu2
            System.out.println(pinyinString);
        } catch (PinyinException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
}

package cn.wahaha.test.javaWebTest;



import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhangrenwei
 * @create: 2019-01-20
 **/

public class JSONObjectTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("df");
        list.add("23");
        JSONObject object = new JSONObject();
        object.put("wechatId", "1234");
        object.put("flagList", list);

        System.out.println(object.toJSONString());
    }

}

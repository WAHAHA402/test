//package cn.wahaha.test;
//
//import cn.wahaha.test.javaWebTest.jackson.model.ExtendableBean;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.core.StringContains;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.StringContains.containsString;
//
///**
// * @Description: JacksonTest
// * @Author: zhangrenwei
// * @Date: 2019/11/14 5:24 下午
// */
//@RunWith(PowerMockRunner.class)
//public class JacksonTest {
//    @Test
//    public void whenSerializingUsingJsonAnyGetter() throws JsonProcessingException {
//        ExtendableBean bean = new ExtendableBean();
//        bean.setProperties(new HashMap<>());
//        bean.getProperties().put("attr1", "val1");
//        bean.getProperties().put("attr2", "val2");
//
//        String result = new ObjectMapper().writeValueAsString(bean);
//        System.out.println(result);
//        assertThat(result, containsString("attr1"));
//        assertThat(result, containsString("attr2"));
//    }
//}

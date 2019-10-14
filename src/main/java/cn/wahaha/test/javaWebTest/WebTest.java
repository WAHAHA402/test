package cn.wahaha.test.javaWebTest;


import cn.wahaha.test.javaWebTest.model.UserDO;
import cn.wahaha.test.javaWebTest.model.UserVO;
import org.springframework.beans.BeanUtils;

/**
 * @description:
 * @author: zhangrenwei
 * @create: 2019-01-10
 **/

public class WebTest {
    public void convertTest(Object source, Object target){

        BeanUtils.copyProperties(source, target);
    }

    public static void main(String[] args) {
        WebTest webTest = new WebTest();
        UserDO userDO = new UserDO("zhangsan", 14);
        UserVO userVO = new UserVO();
        webTest.convertTest(userDO, userVO);
        System.out.println(userVO.toString());
    }
}

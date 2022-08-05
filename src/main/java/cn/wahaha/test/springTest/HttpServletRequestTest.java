package cn.wahaha.test.springTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangrenwei
 * @date 2022/8/2
 * @description 这个类的目的是探究为什么Spring中可以自动注入外部的类HttpServletRequest
 * 探究结果放在这了： <a href="https://www.jianshu.com/p/718c40049282">...</a>
 **/
@RestController
public class HttpServletRequestTest {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/sayHi")
    public void sayHi(String name) {
        System.out.println("hello: " +  name);
    }

    @PostConstruct
    public void after(){
        System.out.println(this.httpServletRequest);
    }

}


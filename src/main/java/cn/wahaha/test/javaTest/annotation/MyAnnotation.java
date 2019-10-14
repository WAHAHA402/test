package cn.wahaha.test.javaTest.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: zhangrenwei
 * @Date: 2019-05-12 19:18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MyAnnotation {
}

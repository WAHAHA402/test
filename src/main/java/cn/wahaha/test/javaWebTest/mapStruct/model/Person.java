package cn.wahaha.test.javaWebTest.mapStruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: Person
 * @Author: zhangrenwei
 * @Date: 2019-06-11 14:38
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    private Long id;
    private String name;
    private String email;
    private long birthday;
    private User user;
}

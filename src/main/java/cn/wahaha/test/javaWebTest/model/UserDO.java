package cn.wahaha.test.javaWebTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zhangrenwei
 * @create: 2019-01-10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    private String username;

    private Integer age;
    
}

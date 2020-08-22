package cn.wahaha.test.javaWebTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @description:
 * @author: zhangrenwei
 * @create: 2019-01-10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    @Length(max = 100)
    private String username;

    private Integer age;
    
}

package cn.wahaha.test.javaWebTest.jackson.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Setter;

import java.util.Map;

/**
 * @Description: ExtendableBean
 * @Author: zhangrenwei
 * @Date: 2019/11/14 5:22 下午
 */
@Setter
public class ExtendableBean {
    public String name;

    private Map<String, String> properties;

    public ExtendableBean() {
    }

    @JsonAnySetter
    public Map<String, String> getProperties() {
        return properties;
    }
}

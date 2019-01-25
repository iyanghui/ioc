package pers.zhixilang.framework.ioc.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author yhui
 * @date 2018-10-16 11:02
 */
@Data
@ToString
public class PropertyArg {
    private String name;

    private String value;

    private String ref;
}

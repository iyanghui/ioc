package com.zhixilang.framework.ioc.bean;

import lombok.Data;
import lombok.ToString;
import java.util.*;

/**
 * 核心数据结构 - 用于描述需要IoC容器管理的对象
 * @author yhui
 * @date 2018-10-16 11:01
 */
@Data
@ToString
public class BeanDefinition {
    private String name;

    private String className;

    private String interfaceClass;

    private List<ConstructorArg> constructorArgs;

    private List<PropertyArg> propertyArgs;

}

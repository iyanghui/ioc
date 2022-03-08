package pers.zhixilang.lego.ioc.bean;


import java.util.List;

/**
 * 核心数据结构 - 用于描述需要IoC容器管理的对象
 * @author zhixilang
 * @date 2018-10-16 11:01
 */
public class BeanDefinition {
    private String name;

    private String className;

    private String interfaceClass;

    private List<ConstructorArg> constructorArgs;

    private List<PropertyArg> propertyArgs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public List<PropertyArg> getPropertyArgs() {
        return propertyArgs;
    }

    public void setPropertyArgs(List<PropertyArg> propertyArgs) {
        this.propertyArgs = propertyArgs;
    }
}

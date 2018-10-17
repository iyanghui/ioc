package com.zhixilang.framework.ioc;


import org.apache.commons.lang3.StringUtils;

/**
 * @author yhui
 * @date 2018-10-16 17:38
 */
public class Application {
    public static void main(String[] args) {

        // TODO 基于annotation
        // http://heeexy.com/2018/01/28/IoC/


        String name = "hello";
        System.out.println(StringUtils.capitalize(name));

    }
}

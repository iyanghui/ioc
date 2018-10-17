package com.zhixilang.framework.ioc.utils;

import java.lang.reflect.Field;

/**
 * 完成对象的依赖注入
 * @author yhui
 * @date 2018-10-16 11:02
 */
public class ReflectionUtils {

    /**
     *
     * @param field
     * @param object context
     * @param value
     */
    public static void injectField(Field field, Object object, Object value) {
        if (field != null) {
            field.setAccessible(true);
            try {
                // 使用反射修改一个变量的值时，编译器不会进行自动装/拆箱。故使用`Field.set(Object , Object)`;
                field.set(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

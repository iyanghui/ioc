package pers.zhixilang.lego.ioc.utils;

/**
 * 类加载工具类
 *
 * 是否破坏了双亲委派原则？？？
 * @author zhixilang
 * @date 2018-10-16 11:02
 */
public class ClassUtils {
    public static Class loadClass(String className) {
        try {
            return getLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ClassLoader getLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}

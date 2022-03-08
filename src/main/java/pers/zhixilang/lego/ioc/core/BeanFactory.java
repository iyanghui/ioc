package pers.zhixilang.lego.ioc.core;

/**
 * @author zhixilang
 * @date 2018-10-16 11:00
 */
public interface BeanFactory {
    /**
     * 获取bean实例
     * @param name
     * @return
     */
    Object getBean(String name);
}

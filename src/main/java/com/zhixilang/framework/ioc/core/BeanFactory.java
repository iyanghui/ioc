package com.zhixilang.framework.ioc.core;

/**
 * @author yhui
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

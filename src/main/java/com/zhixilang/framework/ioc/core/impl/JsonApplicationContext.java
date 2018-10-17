package com.zhixilang.framework.ioc.core.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhixilang.framework.ioc.bean.BeanDefinition;
import com.zhixilang.framework.ioc.core.ApplitionContext;
import com.zhixilang.framework.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * 容器 - 管理BeanFactory
 * @author yhui
 * @date 2018-10-16 11:01
 */
public class JsonApplicationContext extends BeanFactoryImpl implements ApplitionContext {
    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void init() {
        loadFile();
    }

    private void loadFile() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        List<BeanDefinition> beanDefinitionList = JsonUtils.readValue(is,new TypeReference<List<BeanDefinition>>(){});
        if (beanDefinitionList != null && !beanDefinitionList.isEmpty()) {
            for (BeanDefinition bd: beanDefinitionList) {
                registerBean(bd.getName(), bd);
            }
        }
    }

    private String fileName;
}

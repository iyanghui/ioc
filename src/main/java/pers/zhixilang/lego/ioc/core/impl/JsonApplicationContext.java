package pers.zhixilang.lego.ioc.core.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import pers.zhixilang.lego.ioc.bean.BeanDefinition;
import pers.zhixilang.lego.ioc.core.ApplitionContext;
import pers.zhixilang.lego.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * 容器 - 管理BeanFactory
 * @author zhixilang
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

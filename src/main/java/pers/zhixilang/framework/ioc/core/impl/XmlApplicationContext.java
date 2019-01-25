package pers.zhixilang.framework.ioc.core.impl;

import pers.zhixilang.framework.ioc.bean.BeanDefinition;
import pers.zhixilang.framework.ioc.core.ApplitionContext;
import pers.zhixilang.framework.ioc.utils.XmlUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @author yhui
 * @date 2018-10-17 14:44
 */
public class XmlApplicationContext extends BeanFactoryImpl implements ApplitionContext {
    public XmlApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void init() {
        loadFile();
    }

    private void loadFile() {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        try {
            List<BeanDefinition> list = XmlUtils.readBean(in);
            if (list != null && !list.isEmpty()) {
                for (BeanDefinition bd: list) {
                    registerBean(bd.getName(), bd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String fileName;
}

package pers.zhixilang.lego.ioc.core.impl;

import pers.zhixilang.lego.ioc.bean.BeanDefinition;
import pers.zhixilang.lego.ioc.core.ApplitionContext;
import pers.zhixilang.lego.ioc.utils.XmlUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @author zhixilang
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

package pers.zhixilang.framework.ioc.utils;

import pers.zhixilang.framework.ioc.bean.BeanDefinition;
import pers.zhixilang.framework.ioc.bean.PropertyArg;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yhui
 * @date 2018-10-17 14:09
 */
public class XmlUtils {
    public static List<BeanDefinition> readBean (InputStream is) throws Exception {
        SAXReader reader = new SAXReader();
        Document dom;
        try {
            dom = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("parse xml error");
        }
        Element element = dom.getRootElement();
        List<Element> eleBeanList =  element.elements("bean");
        if (eleBeanList == null || eleBeanList.isEmpty()) {
            return null;
        }

        List<BeanDefinition> beanDefinitionList = new LinkedList<>();
        for (Element bean: eleBeanList) {
            BeanDefinition beanDefinition = new BeanDefinition();

            for (Iterator<Attribute> itBean = bean.attributeIterator(); itBean.hasNext();) {
                Attribute attrBean = itBean.next();
                if (null != attrBean.getValue()) {
                    if ("name".equals(attrBean.getName())) {
                        beanDefinition.setName(attrBean.getValue());
                    }
                    if ("class".equals(attrBean.getName())) {
                        beanDefinition.setClassName(attrBean.getValue());
                    }
                }
            }

            List<Element> elePropList = bean.elements("property");

            if (elePropList == null || elePropList.isEmpty()) {
                beanDefinitionList.add(beanDefinition);
                continue;
            }

            List<PropertyArg> propertyArgList = new LinkedList<>();

            for (Element property: elePropList) {
                PropertyArg propertyArg = new PropertyArg();
                for (Iterator<Attribute> itProp = property.attributeIterator(); itProp.hasNext();) {

                    Attribute attrProp = itProp.next();
                    if (null != attrProp.getValue()) {

                        if ("name".equals(attrProp.getName())) {
                            propertyArg.setName(attrProp.getValue());
                        }
                        if ("value".equals(attrProp.getName())) {
                            propertyArg.setValue(attrProp.getValue());
                        }
                        if ("ref".equals(attrProp.getName())) {
                            propertyArg.setRef(attrProp.getValue());
                        }
                    }
                }
                if (null != propertyArg.getName()) {
                    propertyArgList.add(propertyArg);
                }
            }
            beanDefinition.setPropertyArgs(propertyArgList);
            beanDefinitionList.add(beanDefinition);
        }
        return beanDefinitionList;
    }
}

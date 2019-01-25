package pers.zhixilang.framework.ioc.core.impl;

import pers.zhixilang.framework.ioc.bean.BeanDefinition;
import pers.zhixilang.framework.ioc.bean.ConstructorArg;
import pers.zhixilang.framework.ioc.bean.PropertyArg;
import pers.zhixilang.framework.ioc.core.BeanFactory;
import pers.zhixilang.framework.ioc.utils.BeanUtils;
import pers.zhixilang.framework.ioc.utils.ClassUtils;
import pers.zhixilang.framework.ioc.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author yhui
 * @date 2018-10-16 11:00
 */
public class BeanFactoryImpl implements BeanFactory {

    private static final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, BeanDefinition> beanDefineMap = new ConcurrentHashMap<>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());

    private static final ConcurrentHashMap<String, Object> circleDepMap = new ConcurrentHashMap<>();

    /**
     * 获取bean实例
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {
        // 查看对象是否已经被实例化
        Object bean = beanMap.get(name);
        if (bean != null) {
            return bean;
        }

        Object singleBean = circleDepMap.get(name);
        if (singleBean != null) {
            // FIXME 使用`订阅者模式`能否解决`NPE`?
            System.out.println("循环依赖,提前返回依赖的bean " + name);
            return singleBean;
        }

        BeanDefinition beanDefinition = beanDefineMap.get(name);

        try {
            bean = createBean(beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bean != null) {

            circleDepMap.put(name, bean);

            // 注入对象需要的参数
            populateBean(bean, beanDefinition);

            beanMap.put(name, bean);

            circleDepMap.remove(name);
        }

        return bean;
    }

    protected void registerBean(String name, BeanDefinition beanDefinition) {
        beanDefineMap.put(name, beanDefinition);
        beanNameSet.add(name);
    }

    /**
     * 创建对象
     * @param beanDefinition
     * @return
     */
    private Object createBean(BeanDefinition beanDefinition) throws Exception {

        String beanName = beanDefinition.getClassName();

        Class clazz = ClassUtils.loadClass(beanName);

        if (clazz == null) {
            throw new Exception("can not find bean by name, beanName: " + beanName);
        }

        List<ConstructorArg> constructorArgList = beanDefinition.getConstructorArgs();

        if (constructorArgList != null && !constructorArgList.isEmpty()) {
            List<Object> objects = new ArrayList<>();
            for (ConstructorArg constructorArg: constructorArgList) {
                if (constructorArg.getValue() != null) {
                    objects.add(constructorArg.getValue());
                } else {
                    objects.add(getBean(constructorArg.getRef()));
                }
            }
            Class[] constructorArgTypes = objects.stream().map(it -> it.getClass()).collect(Collectors.toList()).toArray(new Class[]{});
            // 根据传递参数,寻找匹配的构造器
            Constructor constructor = clazz.getConstructor(constructorArgTypes);
            return BeanUtils.instanceByCglib(clazz, constructor, objects.toArray());
        } else {
            return BeanUtils.instanceByCglib(clazz, null, null);
        }
    }

    /**
     * 注入对象需要的参数
     * @param bean
     * @param beanDefinition
     */
    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        List<PropertyArg> propertyArgList = beanDefinition.getPropertyArgs();
        if (propertyArgList == null || propertyArgList.isEmpty()) {
            return;
        }

        Class t = bean.getClass();

        Class clazz = bean.getClass().getSuperclass();

        for (PropertyArg propertyArg: propertyArgList) {
            String name = propertyArg.getName();
            String value = propertyArg.getValue();
            String ref = propertyArg.getRef();
            Object injectVal = null;

            if (ref != null) {
                String beanName = StringUtils.uncapitalize(propertyArg.getRef());
                if (beanNameSet.contains(beanName)) {
                    injectVal = getBean(beanName);
                }
            } else if (value != null) {
                injectVal = value;
            }

            try {

                /**
                 * getFields:获得某个类的所有的public的字段，包括父类;
                 * getDeclaredFields获得某个类的所有申明的字段，即包括public、private和proteced,
                 * 但是不包括父类的申明字段
                 */
                Field field = clazz.getDeclaredField(name);
                ReflectionUtils.injectField(field, bean, injectVal);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

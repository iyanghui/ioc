package pers.zhixilang.lego.ioc.core.impl;

import pers.zhixilang.lego.ioc.core.ApplitionContext;

/**
 * isAnnotationPresent(Insert.class)检查注解
 * getAnnotation(Insert.class).value()取得注解内容
 * @author zhixilang
 * @date 2018-10-17 16:47
 */
public class AnnotationApplicationContext implements ApplitionContext {



    @Override
    public void init() {

        // TODO 加载配置文件 -> 扫描

         /* isAnnotationPresent(Insert.class)检查注解
          getAnnotation(Insert.class).value()取得注解内容*/

    }
}

package pers.zhixilang.framework.ioc;

import pers.zhixilang.framework.ioc.core.impl.JsonApplicationContext;
import pers.zhixilang.framework.ioc.core.impl.XmlApplicationContext;
import pers.zhixilang.framework.ioc.entity.Person;

/**
 * @author yhui
 * @date 2018-10-16 11:11
 */
public class Test {
    public static void main(String[] args) {
//        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");

        XmlApplicationContext applicationContext = new XmlApplicationContext("application.xml");

        applicationContext.init();

        Person person = (Person) applicationContext.getBean("person");
        person.start();

    }
}

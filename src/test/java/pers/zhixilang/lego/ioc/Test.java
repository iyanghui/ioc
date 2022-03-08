package pers.zhixilang.lego.ioc;

import pers.zhixilang.lego.ioc.core.impl.XmlApplicationContext;
import pers.zhixilang.lego.ioc.service.Person;

/**
 * @author zhixilang
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

package pers.zhixilang.lego.ioc.service;

import pers.zhixilang.lego.ioc.service.Eat;
import pers.zhixilang.lego.ioc.service.Speak;

/**
 * @author zhixilang
 * @date 2018-10-16 11:11
 */
public class Person {

    private Eat eat;

    private Speak speak;

    public void start() {
        eat.eatMeat();
        speak.speakHello();
    }
}

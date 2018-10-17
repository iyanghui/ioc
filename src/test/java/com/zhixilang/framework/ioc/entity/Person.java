package com.zhixilang.framework.ioc.entity;

/**
 * @author yhui
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

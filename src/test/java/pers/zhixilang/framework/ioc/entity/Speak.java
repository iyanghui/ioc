package pers.zhixilang.framework.ioc.entity;

/**
 * @author yhui
 * @date 2018-10-16 11:12
 */
public class Speak {
    public Speak () {
    }

    public Speak (String name) {
        this.name = name;
    }

    public void speakHello () {
        System.out.println("I'm "+ name +" hello world。。。");
    }

    private String name;
}

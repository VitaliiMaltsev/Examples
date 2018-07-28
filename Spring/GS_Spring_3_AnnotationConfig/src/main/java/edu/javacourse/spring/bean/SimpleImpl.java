package edu.javacourse.spring.bean;

public class SimpleImpl implements SimpleInterface {

    public String sayHello() {
        final String HELLO_WORLD = "Hello, world!";
        System.out.println(HELLO_WORLD);
        return HELLO_WORLD;
    }
}

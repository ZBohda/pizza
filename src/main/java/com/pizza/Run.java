package com.pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {
    public static void main(String... args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    }
}

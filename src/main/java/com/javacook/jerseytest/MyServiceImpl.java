package com.javacook.jerseytest;

public class MyServiceImpl implements MyService {

    public MyServiceImpl() {
        System.out.println("MyServiceImpl-Konstruktor");
    }

    public String getGreeting() {
        return "Joooeerg";
    }
}

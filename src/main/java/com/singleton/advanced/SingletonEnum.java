package com.singleton.advanced;

public enum SingletonEnum {
    INSTANCE;

    // Private constructor to prevent instantiation from other classes
    SingletonEnum() {
        // do the heavy lifting here
    }

    public static SingletonEnum getInstance() {
        return INSTANCE;
    }
}
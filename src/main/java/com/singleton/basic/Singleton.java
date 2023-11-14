package com.singleton.basic;

import java.io.Serializable;

public class Singleton implements Serializable {
    private static volatile Singleton instance;

    private Singleton() {
        // do the heavy lifting here
    }

    public static Singleton getInstance() {
        if (instance == null) { // 1st check
            synchronized (Singleton.class) { // lock once
                if (instance == null) { // 2nd check
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

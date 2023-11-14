package com.singleton.basic;

import java.io.Serializable;

public class SingletonSafeGuarded implements Serializable {

    private static volatile SingletonSafeGuarded instance;

    private SingletonSafeGuarded() {
        if (instance != null) {
            throw new IllegalStateException("instance already present");
        }
        // do the heavy lifting here
    }

    public static SingletonSafeGuarded getInstance() {
        if (instance == null) { // 1st check
            synchronized (SingletonSafeGuarded.class) { // lock once
                if (instance == null) { // 2nd check
                    instance = new SingletonSafeGuarded();
                }
            }
        }
        return instance;
    }

    protected Object readResolve() {
        return instance;
    }
}

package com.singleton.basic;

public class SingletonSafeGuarded {

    private static volatile SingletonSafeGuarded instance;

    private SingletonSafeGuarded() {
        if (instance != null) {
            throw new IllegalStateException("instance already present");
        }
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
}

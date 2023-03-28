package com.singleton.holder;

public class Singleton {

    private Singleton() {
        // do the heavy lifting
    }

    private static class Holder {
        static final Singleton singleton = new Singleton(); // lazily loaded by JVM
    }

    public static Singleton getInstance() {
        return Holder.singleton;
    }
}

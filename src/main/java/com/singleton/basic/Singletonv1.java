package com.singleton.basic;

public class Singletonv1 {

    private static volatile Singletonv1 singletonv1 = null;  // lazy loading

    private Singletonv1() {
        // do the heavy lifting
    }

    public static Singletonv1 getInstance() {
        if (singletonv1 == null) {
            synchronized (Singletonv1.class) {
                if (singletonv1 == null) {
                    singletonv1 = new Singletonv1();
                }
            }
        }
        return singletonv1;
    }

}

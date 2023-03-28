package com.singleton.breaker;

import com.singleton.basic.Singletonv1;

public class SingletonBreaker {
    public static void main(String[] args) {
        Singletonv1 singletonv1_obj1 = Singletonv1.getInstance();
        Singletonv1 singletonv1_obj2 = Singletonv1.getInstance();

        if (singletonv1_obj1 != singletonv1_obj2) {
            System.out.println("Singleton v1 broken!");
        }
    }
}

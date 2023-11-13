package com.singleton.basic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonSafeGuardedTest {

    @Test
    public void testSingletonWithReflection() {
        SingletonSafeGuarded instance1 = SingletonSafeGuarded.getInstance();
        SingletonSafeGuarded instance2 = null;

        try {
            Constructor<SingletonSafeGuarded> constructor = SingletonSafeGuarded.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance2 = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        assertEquals(instance1, instance2);
    }
}

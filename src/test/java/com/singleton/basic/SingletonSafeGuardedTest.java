package com.singleton.basic;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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

    @Test
    public void testSingletonSerialization() {
        // Serialize the singleton instance
        SingletonSafeGuarded instance1 = SingletonSafeGuarded.getInstance();
        byte[] serializedSingleton = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(instance1); // serializing 'instance1'
            serializedSingleton = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Deserializing 'instance1'
        SingletonSafeGuarded instance2 = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedSingleton);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            instance2 = (SingletonSafeGuarded) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        assertSame(instance1, instance2);
        assertEquals(instance1, instance2);
    }
}

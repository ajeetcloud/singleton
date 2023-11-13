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

public class SingletonTest {

    @Test
    public void testSingletonWithReflection() {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = null;

        try {
            Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
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
        Singleton instance1 = Singleton.getInstance();
        byte[] serializedSingleton = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(instance1); // serializing 'instance1'
            serializedSingleton = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Deserializing 'instance1'
        Singleton instance2 = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedSingleton);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            instance2 = (Singleton) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ensure that the deserialized instance is the same as the original instance
        assertSame(instance1, instance2);
    }
}

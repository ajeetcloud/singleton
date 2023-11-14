package advanced;

import com.singleton.advanced.SingletonEnum;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonEnumTest {

    @Test
    public void testEnumSingletonWithReflection() {
        // Attempt to break enum singleton using reflection
        SingletonEnum firstInstance = SingletonEnum.INSTANCE;
        SingletonEnum secondInstance = null;

        try {
            // Use reflection to access the private constructor
            Constructor<SingletonEnum> constructor = SingletonEnum.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            secondInstance = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        // Ensure that enum instances are the same
        assertEquals(firstInstance, secondInstance);
    }

    @Test
    public void testEnumSingletonSerialization() throws IOException, ClassNotFoundException {
        // Serialize the enum singleton instance
        SingletonEnum originalInstance = SingletonEnum.INSTANCE;
        byte[] serializedSingleton;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(originalInstance);
            serializedSingleton = bos.toByteArray();
        }

        // Deserialize the enum singleton instance
        SingletonEnum deserializedInstance;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedSingleton);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            deserializedInstance = (SingletonEnum) in.readObject();
        }

        // Ensure that the deserialized instance is the same as the original instance
        assertSame(originalInstance, deserializedInstance);

        // Ensure that enum instances are equal
        assertEquals(originalInstance, deserializedInstance);
    }
}

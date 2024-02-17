package org.example;

import java.io.*;

public class SerializeUtil {
    /**
     * 序列化
     */
    public static byte[] serialize(Object object) throws IOException {
        try {
            ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteArrayStream);
            objectStream.writeObject(object);
            byte[] bytes = byteArrayStream.toByteArray();
            return bytes;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try {
            ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectStream = new ObjectInputStream(byteArrayStream);
            return objectStream.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}

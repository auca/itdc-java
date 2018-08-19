package main;

import java.io.*;
import java.nio.file.Path;

public class Serializer {

    private Serializer() { }

    static void save(Serializable object, Path path) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }

    static <T> T load(Path path) throws IOException {
        T result;

        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream streamIn = new FileInputStream(path.toFile());
            objectInputStream = new ObjectInputStream(streamIn);

            result = (T) objectInputStream.readObject();
        } catch (ClassCastException | ClassNotFoundException ignored) {
            result = null;
        } finally {
            if (objectInputStream != null){
                objectInputStream.close();
            }
        }

        return result;
    }

}


package repository;

import java.io.*;
import domain.Entity;

public class BinaryFileRepository<T extends Entity> extends Repository<T> {
    private String filePath;

    public BinaryFileRepository(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists();
        loadFromFile();
    }

    private void createFileIfNotExists() {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    @SuppressWarnings("unchecked")
                    T object = (T) inputStream.readObject();
                    add(object);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (T entity : getAll()) {
                outputStream.writeObject(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(T object) {
        super.add(object);
        saveToFile();
    }

    @Override
    public void modify(T newObject) {
        super.modify(newObject);
        saveToFile();
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        saveToFile();
    }
}

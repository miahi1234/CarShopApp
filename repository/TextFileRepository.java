package repository;

import java.io.*;
import domain.Entity;
import domain.Car;

public class TextFileRepository<T extends Entity> extends Repository<T> {
    private String filePath;
    private Class<T> entityType;

    public TextFileRepository(Class<T> entityType, String filePath) {
        this.entityType = entityType;
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
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String maker = parts[1];
                String model = parts[2];

                try {
                    T newEntity = entityType.getDeclaredConstructor(int.class, String.class, String.class)
                                       .newInstance(id, maker, model);
                    add(newEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T entity : getAll()) {
                if (entityType.isInstance(entity)) {
                    try {
                        Car car = (Car) entity;
                        String line = car.getId() + "," + car.getMaker() + "," + car.getModel();
                        writer.write(line);
                        writer.newLine();
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    }
                }
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

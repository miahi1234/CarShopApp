import domain.Car;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import repository.*;

public class PropertiesReader {
    public IRepository<Car> getCarRepositoryFromProperties() {
        Properties properties = new Properties();
        IRepository<Car> carRepository = null;

        try (FileInputStream fis = new FileInputStream("settings.properties")) {
            properties.load(fis);
            String fileName = properties.getProperty("file_name");
            String repositoryType = properties.getProperty("repository_type");

            if (repositoryType.equalsIgnoreCase("text")) {
                carRepository = new TextFileRepository<>(Car.class, fileName);
            } else if (repositoryType.equalsIgnoreCase("binary")) {
                carRepository = new BinaryFileRepository<Car>(fileName);
            }
            else
            {
                System.out.println("Invalid file type in settings.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carRepository;
    }
}
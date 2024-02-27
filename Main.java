import domain.Car;
import repository.IRepository;
import service.Service;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader();
        IRepository<Car> carRepository = propertiesReader.getCarRepositoryFromProperties();

        if (carRepository == null) {
            System.out.println("Failed to initialize car repository from properties.");
            return;
        }

        Service carService = new Service(carRepository);
        UI ui = new UI(carService);

        ui.run();
    }
}
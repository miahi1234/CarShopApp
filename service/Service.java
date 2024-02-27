package service;

import java.util.List;

import domain.Car;
import repository.IRepository;

public class Service {
    private IRepository<Car> carRepository;

    public Service(IRepository<Car> carRepository) {
        this.carRepository = carRepository;
    }

    public void addCar(Car car) {
        this.carRepository.add(car);
    }

    public void removeCar(int carId) {
        this.carRepository.remove(carId);
    }

    public void modifyCar(Car newCar) {
        this.carRepository.modify(newCar);
    }

    public Car getCar(int carId) {
        return this.carRepository.get(carId);
    }

    public List<Car> getAllCars() {
        return this.carRepository.getAll();
    }
}

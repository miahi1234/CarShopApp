package ui;

import service.Service;
import domain.Car;
import java.util.Scanner;

public class UI {
    private Service service;
    private Scanner scanner;

    public UI(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("");
            System.out.println("1. Add a new car");
            System.out.println("2. Remove a car by ID");
            System.out.println("3. Show all cars");
            System.out.println("4. Modify a car");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        addNewCar();
                        break;
                    case 2:
                        removeCarById();
                        break;
                    case 3:
                        showAllCars();
                        break;
                    case 4:
                        modifyCar();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addNewCar() {
        System.out.print("Enter Car ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Car Maker: ");
        String maker = scanner.nextLine();
        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine();
        Car car = new Car(id, maker, model);
        service.addCar(car);
    }

    private void removeCarById() {
        System.out.print("Enter Car ID to remove: ");
        int carId = scanner.nextInt();
        scanner.nextLine();
        service.removeCar(carId);
    }

    private void modifyCar() {    
        System.out.print("Enter Car ID to modify: ");
        int carId = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Enter Car Maker: ");
        String maker = scanner.nextLine();
    
        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine();
    
        Car newCar = new Car(carId, maker, model);
        service.modifyCar(newCar);
    }

    private void showAllCars() {
        System.out.println("All Cars:");
        for (Car car : service.getAllCars()) {
            System.out.println("");
            System.out.println("id: " + Integer.toString(car.getId()));
            System.out.println("maker: " + car.getMaker());
            System.out.println("model: " + car.getModel());
        }
    }
}
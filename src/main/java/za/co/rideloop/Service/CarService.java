package za.co.rideloop.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Car;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Factory.CarFactory;
import za.co.rideloop.Repository.CarRepository;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository repository;


    public Car createCar(Car car) {
        return this.repository.save(car);
    }


    public Car readCar(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    public Car updateCar(Car car) {
        return repository.save(car);
    }

    public Car updateCarLocation(Integer carId, Location newLocation, int distanceTravelled) {
        Car car = readCar(carId);
        if (car == null) return null;

        // Use the factory to rebuild the car
        Car updatedCar = CarFactory.createCar(
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getLicensePlate(),
                car.getRentalRate(),
                car.getStatus(),
                car.getCategory(),
                car.getMileage() + distanceTravelled, // add calculated distance from frontend
                car.getLastMaintenance(),
                car.getMaintenanceDue(),
                newLocation
        );

        return repository.save(updatedCar);
    }



    public void deleteCar(Integer id) {
        this.repository.deleteById(id);
    }


    public List<Car> getAllCars() {
        return this.repository.findAll();
    }


    public List<Car> getCarsByBrand(String brand) {
        return this.repository.findByBrandIgnoreCase(brand);
    }


    public List<Car> getCarsByModel(String model) {
        return this.repository.findByModelIgnoreCase(model);
    }
}
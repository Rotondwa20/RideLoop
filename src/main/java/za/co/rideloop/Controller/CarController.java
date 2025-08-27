package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Car;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    // 🔹 Create a new car
    @PostMapping("/create")
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    // 🔹 Read a car by ID
    @GetMapping("/{id}")
    public Car getCar(@PathVariable Integer id) {
        return carService.readCar(id);
    }

    // 🔹 Update car details (excluding location/mileage)
    @PutMapping("/update")
    public Car updateCar(@RequestBody Car car) {
        return carService.updateCar(car);
    }

    // 🔹 Delete a car by ID
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
    }

    // 🔹 Get all cars
    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // 🔹 Find cars by brand
    @GetMapping("/brand/{brand}")
    public List<Car> getCarsByBrand(@PathVariable String brand) {
        return carService.getCarsByBrand(brand);
    }

    // 🔹 Find cars by model
    @GetMapping("/model/{model}")
    public List<Car> getCarsByModel(@PathVariable String model) {
        return carService.getCarsByModel(model);
    }

    // 🔹 Update car location and mileage
    @PutMapping("/update-location/{id}")
    public Car updateCarLocation(
            @PathVariable Integer id,
            @RequestBody LocationUpdateRequest request
    ) {
        return carService.updateCarLocation(id, request.getLocation(), request.getDistanceTravelled());
    }

    // 🔹 DTO for location update request
    public static class LocationUpdateRequest {
        private Location location;
        private int distanceTravelled; // calculated on frontend

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public int getDistanceTravelled() {
            return distanceTravelled;
        }

        public void setDistanceTravelled(int distanceTravelled) {
            this.distanceTravelled = distanceTravelled;
        }
    }

}

package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.Car;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Factory.CarFactory;
import za.co.rideloop.Service.CarService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CarServiceTest {

    @Autowired
    private CarService service;

    private static Car car;
    private static Location initialLocation;


    @Test
    void a_createCar() {
        initialLocation = new Location.Builder()
                .setLatitude(-33.9189)
                .setLongitude(18.4233)
                .build();

        car = CarFactory.createCar(
                "Toyota",
                "Corolla",
                2020,
                "ABC123",
                250.0,
                "Available",
                "Sedan",
                1000, // initial mileage
                "2024-01-01",
                "2025-01-01",
                initialLocation
        );

        car = service.createCar(car);
        assertNotNull(car);
        System.out.println("Created Car: " + car);
    }


    @Test
    void b_readCar() {
        Car readCar = service.readCar(car.getCarId());
        assertNotNull(readCar);
        System.out.println("Read Car: " + readCar);
    }

    // Update Car details (e.g., status)
    @Test
    void c_updateCar() {
        Car updatedCar = new Car.Builder()
                .setCarId(car.getCarId())
                .setBrand(car.getBrand())
                .setModel(car.getModel())
                .setYear(car.getYear())
                .setLicensePlate(car.getLicensePlate())
                .setRentalRate(car.getRentalRate())
                .setStatus("Rented") // updated status
                .setCategory(car.getCategory())
                .setMileage(car.getMileage())
                .setLastMaintenance(car.getLastMaintenance())
                .setMaintenanceDue(car.getMaintenanceDue())
                .setLocation(car.getLocation())
                .build();

        Car result = service.updateCar(updatedCar);
        assertNotNull(result);
        assertEquals("Rented", result.getStatus());
        System.out.println("Updated Car: " + result);
    }

    //  Update Car location and add mileage
    @Test
    void d_updateCarLocation() {
        Location newLocation = new Location.Builder()
                .setLatitude(-33.9500)
                .setLongitude(18.5000)
                .build();

        int distanceTravelled = 15; // km calculated on frontend

        Car updatedCar = service.updateCarLocation(car.getCarId(), newLocation, distanceTravelled);

        assertNotNull(updatedCar);
        assertEquals(car.getMileage() + distanceTravelled, updatedCar.getMileage());
        assertEquals(newLocation.getLatitude(), updatedCar.getLocation().getLatitude());
        System.out.println("Updated Location and Mileage: " + updatedCar);
    }


    @Test
    void e_getAllCars() {
        List<Car> cars = service.getAllCars();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
        System.out.println("All Cars: " + cars);
    }


    @Test
    void f_deleteCar() {
        service.deleteCar(car.getCarId());
        Car deleted = service.readCar(car.getCarId());
        assertNull(deleted);
        System.out.println("Deleted Car with ID: " + car.getCarId());
    }


    @Test
    void g_findByBrand() {
        Car car1 = CarFactory.createCar(

                "Honda",
                "Civic",
                2019,
                "XYZ456",
                200.0,
                "Available",
                "Sedan",
                500,
                "2024-02-01",
                "2025-02-01",
                initialLocation
        );
        service.createCar(car1);

        List<Car> hondaCars = service.getCarsByBrand("Honda");
        assertNotNull(hondaCars);
        assertTrue(hondaCars.size() > 0);
        System.out.println("Cars by brand 'Honda': " + hondaCars);
    }


    @Test
    void h_findByModel() {
        List<Car> civicCars = service.getCarsByModel("Civic");
        assertNotNull(civicCars);
        assertTrue(civicCars.size() > 0);
        System.out.println("Cars by model 'Civic': " + civicCars);
    }
}

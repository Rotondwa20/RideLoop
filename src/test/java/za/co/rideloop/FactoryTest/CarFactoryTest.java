package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Car;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Factory.CarFactory;
import za.co.rideloop.Factory.LocationFactory;

import static org.junit.jupiter.api.Assertions.*;

/* CarFactoryTest.java

     CAR Factory test class

     Author: Natasha Njili(221785345)

     Date: 18 May 2025 */
public class CarFactoryTest {


    @Test
    void createCar() {
        Location location = new Location.Builder()
                .setLatitude(-33.9249)
                .setLongitude(18.4241)
                .build();

        Car car = CarFactory.createCar(
                89899,
                "Toyota",
                "Corolla",
                2022,
                "CA123456",
                650.00,
                "Available",
                "Sedan",
                12000,
                "2023-08-01",
                "2024-02-01",
                location
        );

        assertNotNull(car);
        assertEquals("Toyota", car.getBrand());
        assertEquals("Corolla", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("CA123456", car.getLicensePlate());
        assertEquals(650.00, car.getRentalRate());
        assertEquals("Available", car.getStatus());
        assertEquals("Sedan", car.getCategory());
        assertEquals(12000, car.getMileage());
        assertEquals("2023-08-01", car.getLastMaintenance());
        assertEquals("2024-02-01", car.getMaintenanceDue());
        assertNotNull(car.getLocation());
    }
}
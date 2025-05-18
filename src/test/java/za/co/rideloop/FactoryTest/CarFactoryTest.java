package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Car;
import za.co.rideloop.Factory.CarFactory;

import static org.junit.jupiter.api.Assertions.*;

/* CarFactoryTest.java

     CAR Factory test class

     Author: Natasha Njili(221785345)

     Date: 18 May 2025 */
public class CarFactoryTest {
    @Test
    void testCreateCar() {

    Car car = CarFactory.createCar(1, "Toyota", "Corolla", 2021,"CA123456",
            450.00, "Available", "Sedan", 30000, "2025-01-15", "2025-06-15"
    );

    assertNotNull(car);
    assertEquals(1, car.toString().contains("carId=1") ? 1 : 0);
    assertTrue(car.toString().contains("brand='Toyota'"));
    assertTrue(car.toString().contains("model='Corolla'"));
    assertTrue(car.toString().contains("licensePlate='CA123456'"));
    assertTrue(car.toString().contains("status='Available'"));
    assertTrue(car.toString().contains("category='Sedan'"));
    assertTrue(car.toString().contains("lastMaintenance='2025-01-15'"));
    assertTrue(car.toString().contains("maintenanceDue='2025-06-15'"));


}


}
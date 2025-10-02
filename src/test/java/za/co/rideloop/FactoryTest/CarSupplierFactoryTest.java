package za.co.rideloop.FactoryTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.CarSupplier;
import za.co.rideloop.Factory.CarSupplierFactory;

import java.time.LocalDate;
import java.util.Date;
/**
 * CarSupplierFactoryTest.java
 * CarSupplierFactoryTest model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */

class CarSupplierFactoryTest {
    @Test
    void testBuildSuccess() {
        CarSupplier supplier = CarSupplierFactory.build(

                "AutoWorld",
                "Kruben Naidoo",
                LocalDate.now(),
                "Active");
        assertNotNull(supplier);
        assertEquals("AutoWorld", supplier.getName());
        assertEquals("Kruben Naidoo", supplier.getContactPerson());

        assertEquals("Active", supplier.getContractStatus());
        System.out.println(supplier.toString());
    }
    @Test
    void testBuildWithEmptyName_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CarSupplierFactory.build(

                    "",
                    "Swatsi Ratia",
                    LocalDate.now(),
                    "Pending");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains("Invalid input"));
        System.err.println(actualMessage);
    }

}
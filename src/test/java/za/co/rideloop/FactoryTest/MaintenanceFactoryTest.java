package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Maintenance;
import za.co.rideloop.Factory.MaintenanceFactory;

import static org.junit.jupiter.api.Assertions.*;
/* MaintenanceFactoryTest.java

     MaintenanceFactory test class

     Author: Natasha Njili(221785345)

     Date: 18 May 2025 */
class MaintenanceFactoryTest {

    @Test
    void createMaintenance_shouldCreateValidMaintenance() {
        Maintenance maintenance = MaintenanceFactory.createMaintenance(
                56L,
                "SAB Insurances",
                "Thabo Mokoena",
                "0821234567",
                "Full Coverage",
                1200.00,
                "Includes engine and electrical repairs"
        );

        assertNotNull(maintenance);
        assertEquals("SA Insurance", maintenance.toString().contains("SAB Insurances") ? "SAB Insurances" : null);
        assertTrue(maintenance.toString().contains("Thabo Mokoena"));
        assertTrue(maintenance.toString().contains("0821234567"));
        assertTrue(maintenance.toString().contains("Full Coverage"));
        assertTrue(maintenance.toString().contains("1200.0"));
        assertTrue(maintenance.toString().contains("Includes engine and electrical repairs"));
    }
}


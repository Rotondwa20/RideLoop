package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Factory.IncidentFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * IncidentFactoryTest.java
 * IncidentFactoryTest model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */

class IncidentFactoryTest {
    @Test
    void testBuildSuccess() {
        Incident incident = IncidentFactory.build(
                1,
                "Accident",
                "Car was rear-ended at signal."
        );

        assertNotNull(incident);
        assertEquals(1, incident.getIncidentID());
        assertEquals("Accident", incident.getIncidentType());
        assertEquals("Car was rear-ended at signal.", incident.getDescription());
    }

    @Test
    void testBuildWithEmptyIncidentType_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            IncidentFactory.build(2, "", "Flat tyre on highway.");
        });

        String expectedMessage = "Invalid input";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testBuildWithNullDescription_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            IncidentFactory.build(3, "Mechanical Failure", null);
        });

        String expectedMessage = "Invalid input";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

}
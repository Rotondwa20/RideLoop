package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Factory.IncidentFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class IncidentFactoryTest {

    @Test
    public void testCreateIncidentSuccessfully() {
        CustomerProfile profile = new CustomerProfile();
        profile.setProfileID(1);
        profile.setFirstName("Alice");
        profile.setLastName("Smith");

        Incident incident = IncidentFactory.createIncident("Security", "Test incident", profile);

        assertNotNull(incident);
        assertEquals("Security", incident.getIncidentType());
        assertEquals("Test incident", incident.getDescription());
        assertEquals(profile, incident.getProfile());
        assertNotNull(incident.getIncidentDate());
    }

    @Test
    public void testCreateIncidentWithInvalidType() {
        CustomerProfile profile = new CustomerProfile();
        profile.setProfileID(2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            IncidentFactory.createIncident("InvalidType", "Some description", profile);
        });

        assertEquals("Incident type must be 'Security' or 'Maintenance'", exception.getMessage());
    }

    @Test
    public void testCreateIncidentWithoutProfile() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            IncidentFactory.createIncident("Security", "No profile", null);
        });

        assertEquals("Incident must be linked to a CustomerProfile", exception.getMessage());
    }
}

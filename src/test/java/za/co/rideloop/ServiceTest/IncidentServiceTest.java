package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Repository.IncidentRepository;
import za.co.rideloop.Service.IIncidentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * IncidentServiceTest.java
 * IncidentServiceTest model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@SpringBootTest
@ActiveProfiles("test")
public class IncidentServiceTest {
    @Autowired
    private IIncidentService service;

    @Autowired
    private IncidentRepository repository;

    private Incident incident;

    @BeforeEach
    void setUp() {
        // Clean table before each test
        repository.deleteAll();

        // Create an initial test incident
        incident = new Incident.Builder()
                .incidentType("Break-in")
                .description("Unauthorized entry at main gate")
                .build();

        incident = service.create(incident);
    }

    @Test
    void testCreate() {
        Incident newIncident = new Incident.Builder()
                .incidentType("Fire")
                .description("Small fire in storage room")
                .build();

        Incident created = service.create(newIncident);

        assertNotNull(created.getIncidentID());
        assertEquals("Fire", created.getIncidentType());
    }

    @Test
    void testRead() {
        Incident found = service.read(incident.getIncidentID());

        assertNotNull(found);
        assertEquals("Break-in", found.getIncidentType());
    }

    @Test
    void testUpdate() {
        incident = new Incident.Builder()
                .incidentID(incident.getIncidentID()) // keep ID
                .incidentType("Burglary")
                .description("Break-in with stolen items")
                .build();

        Incident updated = service.update(incident);

        assertNotNull(updated);
        assertEquals("Burglary", updated.getIncidentType());
        assertEquals("Break-in with stolen items", updated.getDescription());
    }

    @Test
    void testDelete() {
        Integer id = incident.getIncidentID();
        service.delete(id);

        Incident deleted = service.read(id);
        assertNull(deleted); // since service.read returns null if not found
    }

    @Test
    void testGetAll() {
        List<Incident> allIncidents = service.getAll();

        assertFalse(allIncidents.isEmpty());
        assertEquals(1, allIncidents.size());
    }
}

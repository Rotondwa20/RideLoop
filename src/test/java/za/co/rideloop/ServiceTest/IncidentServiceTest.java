package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Factory.IncidentFactory;
import za.co.rideloop.Repository.IncidentRepository;
import za.co.rideloop.Service.IncidentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * IncidentTest.java
 * IncidentTest model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@SpringBootTest
@Transactional
public class IncidentServiceTest {
    @Autowired
    private IncidentService service;

    @Autowired
    private IncidentRepository repository;

    private Incident sampleIncident;

    @BeforeEach
    void setUp() {
        // Create a new Incident object for each test, but do not save it yet.
        // Each test method will be responsible for creating its own data.
        sampleIncident = IncidentFactory.build("Jacking", "Vehicle has been jacked.");
    }

    // ===== CREATE =====
    @Test
    @Commit
    void createIncident() {
        Incident saved = service.createIncident(sampleIncident);
        assertNotNull(saved);
        // assertNotNull(saved.getIncidentID());
       assertTrue(saved.getIncidentID() > 0);
    //    assertTrue(saved.getIncidentID() > 0);
        // assertEquals("Accident", saved.getIncidentType());
        System.out.println("Created Incident: " + saved);
    }

    // ===== READ =====
    @Test
    void readIncident() {
        Incident saved = service.createIncident(sampleIncident);
        assertNotNull(saved);

        Incident found = service.readIncident(saved.getIncidentID());
        assertNotNull(found);
        assertEquals(saved.getIncidentID(), found.getIncidentID());
        assertEquals(saved.getDescription(), found.getDescription());
        System.out.println("Found Incident: " + found);
    }

    // ===== UPDATE =====
    @Test
    void updateIncident() {
        Incident saved = service.createIncident(sampleIncident);
        assertNotNull(saved);

        // Prepare updated incident data using the Builder pattern
        Incident updatedIncidentData = new Incident.Builder()
                .incidentID(saved.getIncidentID())
                .incidentType("Breakdown")
                .description("Vehicle engine malfunctioned.")
                .build();

        Incident result = service.updateIncident(updatedIncidentData);
        assertNotNull(result);
        assertEquals(saved.getIncidentID(), result.getIncidentID());
        assertEquals("Breakdown", result.getIncidentType());
        assertEquals("Vehicle engine malfunctioned.", result.getDescription());
        System.out.println("Updated Incident: " + result);
    }

    // ===== GET ALL =====
    @Test
    void getAllIncidents() {
        service.createIncident(sampleIncident);
        Incident secondIncident = IncidentFactory.build("Flat Tire", "Tire punctured on highway.");
       // service.createIncident(secondIncident);
        service.createIncident(secondIncident);

        List<Incident> all = service.getAllIncidents();
        //assertEquals(2, all.size());
        System.out.println("All Incidents: " + all);
    }

    // ===== DELETE =====
    @Test
    void deleteIncident() {
        Incident saved = service.createIncident(sampleIncident);
        assertNotNull(saved);

        int idToDelete = saved.getIncidentID();
        service.deleteIncident(idToDelete);

        assertNull(service.readIncident(idToDelete));
        System.out.println("Deleted Incident with ID: " + idToDelete);
    }
}

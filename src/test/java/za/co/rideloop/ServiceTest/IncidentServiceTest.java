//package za.co.rideloop.ServiceTest;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import za.co.rideloop.Domain.Incident;
//import za.co.rideloop.Domain.User;
//import za.co.rideloop.Repository.UserRepository;
//import za.co.rideloop.Service.IncidentService;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional // rollback after each test
//public class IncidentServiceTest {
//
//    @Autowired
//    private IncidentService incidentService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private User testUser;
//
//    @BeforeEach
//    public void setUp() {
//        // create and save a test user
//        testUser = new User.Builder()
//                .setUsername("Test User")
//                .setEmail("testuser@example.com")
//                .setPassword("password")
//                .build();
//
//        userRepository.save(testUser);
//    }
//
//    @Test
//    public void testCreateIncident() {
//        Incident incident = incidentService.createIncident("Security", "Test incident", testUser);
//
//        assertNotNull(incident);
//        assertEquals("Security", incident.getIncidentType());
//        assertEquals("Test incident", incident.getDescription());
//        assertEquals(testUser.getUserID(), incident.getUser().getUserID());
//        assertNotNull(incident.getIncidentDate());
//    }
//
//    @Test
//    public void testGetAllIncidents() {
//        incidentService.createIncident("Security", "Incident 1", testUser);
//        incidentService.createIncident("Maintenance", "Incident 2", testUser);
//
//        List<Incident> incidents = incidentService.getAllIncidents();
//        assertTrue(incidents.size() >= 2); // may include existing data
//    }
//
//    @Test
//    public void testGetIncidentsByUser() {
//        incidentService.createIncident("Security", "Incident 1", testUser);
//
//        List<Incident> userIncidents = incidentService.getIncidentsByUser(testUser);
//        assertTrue(userIncidents.size() >= 1);
//        assertEquals("Incident 1", userIncidents.get(0).getDescription());
//    }
//}

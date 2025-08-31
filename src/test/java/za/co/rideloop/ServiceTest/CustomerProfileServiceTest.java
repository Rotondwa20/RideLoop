package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Repository.CustomerProfileRepository;
import za.co.rideloop.Repository.UserRepository;
import za.co.rideloop.Service.CustomerProfileService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerProfileServiceTest {

    @Autowired
    private CustomerProfileService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerProfileRepository profileRepository;

    private User testUser;

    @BeforeEach
    public void setup() {
        // Clean DB
        profileRepository.deleteAll();
        userRepository.deleteAll();

        // Create a test user
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser = userRepository.save(testUser);
    }

    @Test
    public void testCreateProfile_DefaultStatusPending() {
        CustomerProfile profile = new CustomerProfile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setPhoneNumber("123456789");
        profile.setAddress(new Address());

        CustomerProfile saved = service.createProfileForUser(profile, testUser.getUserID());

        assertNotNull(saved.getProfileID());
        assertEquals("pending", saved.getStatus(), "Status should default to 'pending'");
        assertEquals(testUser.getUserID(), saved.getUser().getUserID());
    }

    @Test
    public void testUpdateProfile_NonAdminCannotChangeStatus() {
        CustomerProfile profile = new CustomerProfile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setPhoneNumber("123456789");
        profile.setAddress(new Address());

        CustomerProfile saved = service.createProfileForUser(profile, testUser.getUserID());

        saved.setStatus("approved"); // User tries to approve
        saved.setFirstName("Johnny"); // Change allowed

        CustomerProfile updated = service.updateProfile(saved, false);

        assertEquals("pending", updated.getStatus(), "Non-admin cannot change status");
        assertEquals("Johnny", updated.getFirstName());
    }

    @Test
    public void testUpdateProfile_AdminCanChangeStatus() {
        CustomerProfile profile = new CustomerProfile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setPhoneNumber("123456789");
        profile.setAddress(new Address());

        CustomerProfile saved = service.createProfileForUser(profile, testUser.getUserID());

        saved.setStatus("approved"); // Admin approves
        CustomerProfile updated = service.updateProfile(saved, true);

        assertEquals("approved", updated.getStatus(), "Admin should be able to approve profile");
    }

    @Test
    public void testFindProfilesByStatus() {
        CustomerProfile profile1 = new CustomerProfile();
        profile1.setFirstName("A");
        profile1.setStatus("pending");
        service.createProfileForUser(profile1, testUser.getUserID());

        List<CustomerProfile> pendingProfiles = service.findProfilesByStatus("pending");
        assertFalse(pendingProfiles.isEmpty());
        assertEquals("pending", pendingProfiles.get(0).getStatus());
    }
}

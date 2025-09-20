//package za.co.rideloop.ServiceTest;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import za.co.rideloop.Domain.Address;
//import za.co.rideloop.Domain.CustomerProfile;
//import za.co.rideloop.Domain.User;
//import za.co.rideloop.Repository.CustomerProfileRepository;
//import za.co.rideloop.Repository.UserRepository;
//import za.co.rideloop.Service.CustomerProfileService;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional  // Rollback after each test
//class CustomerProfileServiceIntegrationTest {
//
//    @Autowired
//    private CustomerProfileService service;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CustomerProfileRepository profileRepository;
//
//    private User testUser;
//
//    @BeforeEach
//    void setUp() {
//        // Create and save a test user
//        testUser = new User();
//        testUser.setFirstName("John");
//        testUser.setLastName("Doe");
//        testUser.setEmail("john@example.com");
//        testUser.setPhone("1234567890");
//        userRepository.save(testUser);
//    }
//
//    @Test
//    void testCreateProfileForUser() {
//        CustomerProfile profile = new CustomerProfile();
//        profile.setFirstName("John");
//        profile.setLastName("Doe");
//        profile.setPhoneNumber("1234567890");
//        profile.setAddress(new Address("Street 1", "Suburb", "Province", "0001"));
//
//        CustomerProfile savedProfile = service.createProfileForUser(profile, testUser.getUserID());
//
//        assertNotNull(savedProfile.getProfileID(), "Profile ID should not be null");
//        assertEquals("pending", savedProfile.getStatus(), "Default status should be 'pending'");
//        assertEquals(testUser.getUserID(), savedProfile.getUser().getUserID(), "Profile user ID should match test user");
//    }
//
//    @Test
//    void testUpdateProfile() {
//        CustomerProfile profile = new CustomerProfile();
//        profile.setFirstName("John");
//        profile.setLastName("Doe");
//        profile.setUser(testUser);
//        profile.setStatus("pending");
//        profileRepository.save(profile);
//
//        // Update profile
//        profile.setFirstName("Jane");
//        profile.setStatus("approved");
//
//        CustomerProfile updated = service.updateProfile(profile, true);
//
//        assertEquals("Jane", updated.getFirstName(), "First name should be updated");
//        assertEquals("approved", updated.getStatus(), "Status should be updated by admin");
//    }
//
//    @Test
//    void testGetProfilesByStatus() {
//        CustomerProfile profile = new CustomerProfile();
//        profile.setFirstName("John");
//        profile.setLastName("Doe");
//        profile.setUser(testUser);
//        profile.setStatus("active");
//        profileRepository.save(profile);
//
//        List<CustomerProfile> profiles = service.getProfilesByStatus("active");
//
//        assertEquals(1, profiles.size(), "Should find 1 profile with status 'active'");
//        assertEquals("John", profiles.get(0).getFirstName());
//        assertEquals("john@example.com", profiles.get(0).getUser().getEmail());
//    }
//
//    @Test
//    void testGetFullProfile() {
//        CustomerProfile profile = new CustomerProfile();
//        profile.setFirstName("John");
//        profile.setLastName("Doe");
//        profile.setUser(testUser);
//        profileRepository.save(profile);
//
//        CustomerProfile fullProfile = service.getFullProfile(testUser.getUserID());
//
//        assertEquals("John", fullProfile.getFirstName());
//        assertEquals("john@example.com", fullProfile.getUser().getEmail());
//        assertEquals(testUser.getPhone(), fullProfile.getUser().getPhone());
//    }
//
//    @Test
//    void testGetAllProfiles() {
//        // Create a second user and profile
//        User user2 = new User();
//        user2.setFirstName("Alice");
//        user2.setLastName("Smith");
//        user2.setEmail("alice@example.com");
//        user2.setPhone("9876543210");
//        userRepository.save(user2);
//
//        CustomerProfile profile1 = new CustomerProfile();
//        profile1.setFirstName("John");
//        profile1.setLastName("Doe");
//        profile1.setUser(testUser);
//        profile1.setStatus("active");
//        profileRepository.save(profile1);
//
//        CustomerProfile profile2 = new CustomerProfile();
//        profile2.setFirstName("Alice");
//        profile2.setLastName("Smith");
//        profile2.setUser(user2);
//        profile2.setStatus("pending");
//        profileRepository.save(profile2);
//
//        List<CustomerProfile> allProfiles = service.getAllProfiles();
//        assertEquals(2, allProfiles.size());
//        assertTrue(allProfiles.stream().anyMatch(p -> p.getUser().getEmail().equals("john@example.com")));
//        assertTrue(allProfiles.stream().anyMatch(p -> p.getUser().getEmail().equals("alice@example.com")));
//    }
//
//    @Test
//    void testDeleteProfile() {
//        CustomerProfile profile = new CustomerProfile();
//        profile.setFirstName("John");
//        profile.setLastName("Doe");
//        profile.setUser(testUser);
//        profileRepository.save(profile);
//
//        service.deleteProfile(profile.getProfileID());
//        assertFalse(profileRepository.findById(profile.getProfileID()).isPresent(), "Profile should be deleted");
//    }
//
//    @Test
//    void testGetFullProfileNotFound() {
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getFullProfile(9999));
//        assertEquals("Profile not found for user ID: 9999", exception.getMessage());
//    }
//}

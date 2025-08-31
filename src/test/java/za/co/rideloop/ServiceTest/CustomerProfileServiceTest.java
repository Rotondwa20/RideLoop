package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Repository.UserRepository;
import za.co.rideloop.Service.CustomerProfileService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerProfileServiceTest {

    @Autowired
    private CustomerProfileService profileService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private CustomerProfile testProfile;

    @BeforeEach
    void setup() {
        // Create and persist a test user
        testUser = new User.Builder()
                .setUsername("testuser")
                .setEmail("test@example.com")
                .setPassword("password")
                .build();
        testUser = userRepository.save(testUser);

        // Create an address
        Address address = new Address();
        address.setStreetName("123 Main St");
        address.setSuburb("Central");
        address.setProvince("Gauteng");
        address.setZipCode("2000");

        // Create a profile linked to the user
        testProfile = new CustomerProfile.Builder()
                .setUser(testUser)
                .setFirstName("John")
                .setLastName("Doe")
                .setIdNumber("1234567890123")
                .setLicenseNumber("L123456")
                .setPhoneNumber("0123456789")
                .setAddress(address)
                .build();

        // Persist using createProfile (or saveProfileForUser)
        testProfile = profileService.createProfile(testProfile);
    }

    @Test
    void testCreateProfile() {
        assertNotNull(testProfile);
        assertNotNull(testProfile.getProfileID());
        assertEquals("John", testProfile.getFirstName());
        assertEquals(testUser.getUsername(), testProfile.getUser().getUsername());
    }

    @Test
    void testSaveProfileForUser() {
        testProfile.setFirstName("Johnny");
        CustomerProfile updated = profileService.saveProfileForUser(testUser.getUserID(), testProfile);

        assertEquals("Johnny", updated.getFirstName());
        assertEquals(testProfile.getProfileID(), updated.getProfileID());
    }

    @Test
    void testReadProfile() {
        CustomerProfile found = profileService.readProfile(testProfile.getProfileID());
        assertNotNull(found);
        assertEquals("John", found.getFirstName());
    }

    @Test
    void testUpdateProfile() {
        testProfile.setFirstName("Johnny");
        CustomerProfile updated = profileService.updateProfile(testProfile);

        assertEquals("Johnny", updated.getFirstName());
        assertEquals(testProfile.getProfileID(), updated.getProfileID());
    }

    @Test
    void testDeleteProfile() {
        profileService.deleteProfile(testProfile.getProfileID());

        CustomerProfile deleted = profileService.readProfile(testProfile.getProfileID());
        assertNull(deleted);
    }

    @Test
    void testGetAllProfiles() {
        List<CustomerProfile> profiles = profileService.getAllProfiles();
        assertTrue(profiles.size() >= 1);
    }
}

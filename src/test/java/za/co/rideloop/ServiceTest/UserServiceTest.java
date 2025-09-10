package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Service.UserService;
import za.co.rideloop.Repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // Rollback after each test
@ActiveProfiles("test")  // optional: use a test-specific application-test.properties
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User.Builder()
                .setUsername("Test User")
                .setEmail("testuser@example.com")
                .setPassword("password")
                .build();
    }

    @Test
    public void testRegisterUserSuccessfully() {
        String result = userService.registerUser(testUser, null);
        assertEquals("User registered successfully", result);

        Optional<User> savedUser = userRepository.findByEmail("testuser@example.com");
        assertTrue(savedUser.isPresent());
        assertEquals("Test User", savedUser.get().getUsername());
    }

    @Test
    public void testRegisterUserWithExistingEmail() {
        userService.registerUser(testUser, null);
        User duplicateUser = new User.Builder()
                .setUsername("Another User")
                .setEmail("testuser@example.com")
                .setPassword("password123")
                .build();

        String result = userService.registerUser(duplicateUser, null);
        assertEquals("Email already exists", result);
    }

    @Test
    public void testLoginUserSuccessfully() {
        userService.registerUser(testUser, null);

        Optional<User> loginResult = userService.loginUser("testuser@example.com", "password");
        assertTrue(loginResult.isPresent());
        assertEquals("Test User", loginResult.get().getUsername());
    }

    @Test
    public void testLoginUserWithWrongPassword() {
        userService.registerUser(testUser, null);

        Optional<User> loginResult = userService.loginUser("testuser@example.com", "wrongpassword");
        assertTrue(loginResult.isEmpty());
    }

    @Test
    public void testDeleteUserById() {
        userService.registerUser(testUser, null);
        int userId = userRepository.findByEmail("testuser@example.com").get().getUserID();

        userService.deleteById(userId);

        Optional<User> deletedUser = userRepository.findById(userId);
        assertTrue(deletedUser.isEmpty());
    }
}

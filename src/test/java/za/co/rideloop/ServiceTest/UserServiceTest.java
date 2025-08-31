package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Rollback
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private User customerUser;
    private User adminUser;

    @BeforeEach
    void setup() {
        // Customer user
        customerUser = new User();
        customerUser.setEmail("customer@test.com");
        customerUser.setPassword("password");
        customerUser.setRole("CUSTOMER");

        // Admin user
        adminUser = new User();
        adminUser.setEmail("admin@test.com");
        adminUser.setPassword("password");
        adminUser.setRole("ADMIN");
    }

    @Test
    void testRegisterCustomerSuccess() {
        String message = userService.registerUser(customerUser, null);
        assertEquals("User registered successfully", message);

        Optional<User> saved = userService.findById(customerUser.getUserID());
        assertTrue(saved.isPresent());
        assertEquals("CUSTOMER", saved.get().getRole());
    }

    @Test
    void testRegisterCustomerEmailExists() {
        userService.registerUser(customerUser, null);
        String message = userService.registerUser(customerUser, null);
        assertEquals("Email already exists", message);
    }

    @Test
    void testRegisterAdminWithCorrectCode() {
        String message = userService.registerUser(adminUser, "SECRET123"); // Ensure property matches
        assertEquals("User registered successfully", message);
    }

    @Test
    void testRegisterAdminWithWrongCode() {
        String message = userService.registerUser(adminUser, "WRONGCODE");
        assertEquals("Invalid security code for ADMIN registration", message);
    }

    @Test
    void testValidateUserSuccess() {
        userService.registerUser(customerUser, null);
        String message = userService.validateUser("customer@test.com", "password");
        assertEquals("Login successful", message);
    }

    @Test
    void testValidateUserWrongPassword() {
        userService.registerUser(customerUser, null);
        String message = userService.validateUser("customer@test.com", "wrongpass");
        assertEquals("Invalid password", message);
    }

    @Test
    void testValidateUserNotFound() {
        String message = userService.validateUser("nonexistent@test.com", "password");
        assertEquals("User not found", message);
    }

    @Test
    void testDeleteUser() {
        userService.registerUser(customerUser, null);
        int id = customerUser.getUserID();
        assertTrue(userService.findById(id).isPresent());

        userService.deleteById(id);
        assertTrue(userService.findById(id).isEmpty());
    }
}

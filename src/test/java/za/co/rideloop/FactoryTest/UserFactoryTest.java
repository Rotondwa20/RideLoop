package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Factory.UserFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserFactoryTest {

    @Test
    void testCreateCustomer() {
        User customer = UserFactory.createCustomer("john_doe", "john@example.com", "password123");

        assertNotNull(customer);
        assertEquals("john_doe", customer.getUsername());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("password123", customer.getPassword());
        assertEquals("CUSTOMER", customer.getRole());
    }

    @Test
    void testCreateAdmin() {
        User admin = UserFactory.createAdmin("admin_user", "admin@example.com", "adminpass");

        assertNotNull(admin);
        assertEquals("admin_user", admin.getUsername());
        assertEquals("admin@example.com", admin.getEmail());
        assertEquals("adminpass", admin.getPassword());
        assertEquals("ADMIN", admin.getRole());
    }
}

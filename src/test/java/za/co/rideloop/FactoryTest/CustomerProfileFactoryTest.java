package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Factory.CustomerProfileFactory;

import static org.junit.jupiter.api.Assertions.*;

class CustomerProfileFactoryTest {

    @Test
    void testCreateCustomerProfile_Basic() {
        // Arrange
        User user = new User.Builder()
                .setUsername("basicuser")
                .setEmail("basic@example.com")
                .setPassword("password")
                .build();

        String firstName = "John";
        String lastName = "Doe";
        String idNumber = "1234567890123";
        String phoneNumber = "0712345678";
        String status = "pending";

        // Act
        CustomerProfile profile = CustomerProfileFactory.createCustomerProfile(
                user, firstName, lastName, idNumber, phoneNumber, status
        );

        // Assert
        assertNotNull(profile);
        assertEquals(user, profile.getUser());
        assertEquals(firstName, profile.getFirstName());
        assertEquals(lastName, profile.getLastName());
        assertEquals(idNumber, profile.getIdNumber());
        assertEquals(phoneNumber, profile.getPhoneNumber());
        assertEquals(status, profile.getStatus());
        assertNull(profile.getAddress());
    }

    @Test
    void testCreateCustomerProfile_Full() {
        // Arrange
        User user = new User.Builder()
                .setUsername("fulluser")
                .setEmail("full@example.com")
                .setPassword("securepass")
                .build();

        String firstName = "Alice";
        String lastName = "Smith";
        String idNumber = "9876543210987";
        String licenseNumber = "L123456789";
        String phoneNumber = "0798765432";
        String status = "pending";

        Address address = new Address.Builder()
                .setStreetName("123 Main St")
                .setSuburb("Cape Town")
                .setProvince("Western Cape")
                .setZipCode("8001")
                .build();

        // Act
        CustomerProfile profile = CustomerProfileFactory.createFullCustomerProfile(
                user, firstName, lastName, idNumber, licenseNumber, phoneNumber, address, status
        );

        // Assert
        assertNotNull(profile);
        assertEquals(user, profile.getUser());
        assertEquals(firstName, profile.getFirstName());
        assertEquals(lastName, profile.getLastName());
        assertEquals(idNumber, profile.getIdNumber());
        assertEquals(licenseNumber, profile.getLicenseNumber());
        assertEquals(phoneNumber, profile.getPhoneNumber());
        assertEquals(address, profile.getAddress());
        assertEquals(status, profile.getStatus());
    }
}

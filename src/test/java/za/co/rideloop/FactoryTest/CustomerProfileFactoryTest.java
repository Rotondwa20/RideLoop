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

        // Act
        CustomerProfile profile = CustomerProfileFactory.createCustomerProfile(
                user, firstName, lastName, idNumber, phoneNumber
        );

        // Assert
        assertNotNull(profile);
        assertEquals(user, profile.getUser());
        assertEquals(firstName, profile.getFirstName());
        assertEquals(lastName, profile.getLastName());
        assertEquals(idNumber, profile.getIdNumber());
        assertEquals(phoneNumber, profile.getPhoneNumber());
        assertNull(profile.getAddress());
        assertNull(profile.getProfileImage());
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

        Address address = new Address.Builder()
                .setStreetName("123 Main St")
                .setSuburb("Cape Town")   // was setCity()
                .setProvince("Western Cape") // instead of setCountry()
                .setZipCode("8001")       // was setPostalCode()
                .build();



        byte[] idDocument = new byte[]{1, 2, 3};
        byte[] licenseDocument = new byte[]{4, 5, 6};
        byte[] proofOfAddress = new byte[]{7, 8, 9};
        byte[] profileImage = new byte[]{10, 11, 12};

        // Act
        CustomerProfile profile = CustomerProfileFactory.createFullCustomerProfile(
                user, firstName, lastName, idNumber, licenseNumber, phoneNumber,
                address, idDocument, licenseDocument, proofOfAddress, profileImage
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
        assertArrayEquals(idDocument, profile.getIdDocument());
        assertArrayEquals(licenseDocument, profile.getLicenseDocument());
        assertArrayEquals(proofOfAddress, profile.getProofOfAddress());
        assertArrayEquals(profileImage, profile.getProfileImage());
    }
}

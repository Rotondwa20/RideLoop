package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerFactoryTest {

    @Test
    public void testCreateCustomer() {

        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "0821234567";
        String licenseNumber = "L1234567";
        String username = "johndoe";
        String password = "password123";
        String status = "ACTIVE";
        String streetName = "123 Main St";
        String suburb = "Central";
        String province = "Western Cape";
        String zipCode = "8001";
        String contactEmail = "contact@example.com";
        String contactNumber = "0827654321";
        String alternativeContactNum = "0830001111";

        Customer customer = CustomerFactory.createCustomer(
                firstName, lastName, email, phone, licenseNumber,
                username, password, status,
                streetName, suburb, province, zipCode,
                contactEmail, contactNumber, alternativeContactNum
        );

        assertNotNull(customer, "Customer should not be null");
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(email, customer.getEmail());
        assertEquals(phone, customer.getPhone());
        assertEquals(licenseNumber, customer.getLicenseNumber());
        assertEquals(username, customer.getUsername());
        assertEquals(status, customer.getStatus());
        assertNotEquals(password, customer.getPassword());

        assertNotNull(customer.getAddress());
        assertEquals(streetName, customer.getAddress().getStreetName());
        assertEquals(suburb, customer.getAddress().getSuburb());
        assertEquals(province, customer.getAddress().getProvince());
        assertEquals(zipCode, customer.getAddress().getZipCode());

        assertNotNull(customer.getContactDetails());
        assertEquals(contactEmail, customer.getContactDetails().getContactEmail());
        assertEquals(contactNumber, customer.getContactDetails().getContactNumber());
        assertEquals(alternativeContactNum, customer.getContactDetails().getAlternativeContactNum());
    }
}

package za.co.rideloop.FactoryTest;

/**
 * CustomerFactory.java

 * Author:Rotondwa Rambau
 * Student Number: 222342145
 * Group:3I
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.ContactDetails;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Factory.CustomerFactory;

public class CustomerFactoryTest {

    @Test
    public void testCreateCustomer() {
        Customer customer = CustomerFactory.createCustomer(
                101,
                "Tondy",
                "Rambau",
                "tondy@example.com",
                "0720000000",
                "L123456789",
                "tondy_r",
                "Password",
                "Active",
                "123 Hope Street",
                "Ennerdale",
                "Gauteng",
                "1830",
                "contact@example.com",
                "0812345678",
                "0712345678"
        );

        assertNotNull(customer);
        assertEquals(101, customer.getCustomerID());
        assertEquals("Tondy", customer.getFirstName());
        assertEquals("Rambau", customer.getLastName());
        assertEquals("tondy@example.com", customer.getEmail());
        assertEquals("0720000000", customer.getPhone());
        assertEquals("L123456789", customer.getLicenseNumber());
        assertEquals("tondy_r", customer.getUsername());
        assertEquals("securePassword", customer.getPassword());
        assertEquals("Active", customer.getStatus());

        Address address = customer.getAddress();
        assertNotNull(address);
        assertEquals("123 Hope Street", address.getStreetName());
        assertEquals("Ennerdale", address.getSuburb());
        assertEquals("Gauteng", address.getProvince());
        assertEquals("1830", address.getZipCode());

        ContactDetails contactDetails = customer.getContactDetails();
        assertNotNull(contactDetails);
        assertEquals("contact@example.com", contactDetails.getContactEmail());
        assertEquals("0812345678", contactDetails.getContactNumber());
        assertEquals("0712345678", contactDetails.getAlternativeContactNum());
    }
}

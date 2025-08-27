package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.ContactDetails;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Service.CustomerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john@example.com")
                .setPhone("1234567890")
                .setLicenseNumber("LIC123")
                .setUsername("johndoe")
                .setPassword("12345")
                .setStatus("ACTIVE")
                .build();

        Customer saved = customerService.createCustomer(customer);

        assertNotNull(saved);
        assertNotNull(saved.getCustomerID());
        assertEquals("John", saved.getFirstName());
    }

    @Test
    void testCreateCustomerWithAddressAndContact() {
        Address address = new Address.Builder()
                .setStreetName("123 Main St")
                .setSuburb("Central")
                .setProvince("Gauteng")
                .setZipCode("2000")
                .build();

        ContactDetails contactDetails = new ContactDetails.Builder()
                .setContactEmail("john.contact@example.com")
                .setContactNumber("0123456789")
                .setAlternativeContactNum("0987654321")
                .build();

        Customer customer = new Customer.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john@example.com")
                .setPhone("1234567890")
                .setLicenseNumber("LIC123")
                .setUsername("johndoe")
                .setPassword("12345")
                .setStatus("ACTIVE")
                .setAddress(address)
                .setContactDetails(contactDetails)
                .build();

        Customer saved = customerService.createCustomer(customer);

        assertNotNull(saved.getCustomerID());
        assertEquals("123 Main St", saved.getAddress().getStreetName());
        assertEquals("john.contact@example.com", saved.getContactDetails().getContactEmail());
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        assertNotNull(customers);
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer.Builder()
                .setFirstName("Jane")
                .setLastName("Smith")
                .setEmail("jane@example.com")
                .build();

        Customer saved = customerService.createCustomer(customer);
        saved.setLastName("Johnson");

        Customer updated = customerService.updateCustomer(saved);
        assertEquals("Johnson", updated.getLastName());
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer.Builder()
                .setFirstName("Mark")
                .setLastName("Twain")
                .build();

        Customer saved = customerService.createCustomer(customer);
        Long id = saved.getCustomerID();

        customerService.delete(id);

        Customer deleted = customerService.readCustomer(id);
        assertNull(deleted);
    }

    @Test
    void testReadCustomer() {
        Customer customer = new Customer.Builder()
                .setFirstName("Alice")
                .setLastName("Wonder")
                .build();

        Customer saved = customerService.createCustomer(customer);
        Customer found = customerService.readCustomer(saved.getCustomerID());

        assertNotNull(found);
        assertEquals("Alice", found.getFirstName());
    }
}

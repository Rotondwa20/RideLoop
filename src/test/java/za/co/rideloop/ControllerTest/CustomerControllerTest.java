package za.co.rideloop.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import za.co.rideloop.Domain.Customer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createCustomerTest() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        Customer created = restTemplate.postForObject("/api/customer/create", customer, Customer.class);

        assertThat(created).isNotNull();
        assertThat(created.getCustomerID()).isNotNull();
        assertThat(created.getFirstName()).isEqualTo("John");
    }

    @Test
    void readCustomerTest() {
        Customer customer = new Customer();
        customer.setFirstName("Alice");
        Customer created = restTemplate.postForObject("/api/customer/create", customer, Customer.class);

        Customer found = restTemplate.getForObject("/api/customer/" + created.getCustomerID(), Customer.class);
        assertThat(found).isNotNull();
        assertThat(found.getFirstName()).isEqualTo("Alice");
    }

    @Test
    void updateCustomerTest() {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        Customer created = restTemplate.postForObject("/api/customer/create", customer, Customer.class);

        created.setFirstName("Janet");
        HttpEntity<Customer> entity = new HttpEntity<>(created);
        Customer updated = restTemplate.exchange("/api/customer/update", HttpMethod.PUT, entity, Customer.class).getBody();

        assertThat(updated.getFirstName()).isEqualTo("Janet");
    }

    @Test
    void deleteCustomerTest() {
        Customer customer = new Customer();
        customer.setFirstName("Mark");
        Customer created = restTemplate.postForObject("/api/customer/create", customer, Customer.class);

        restTemplate.delete("/api/customer/" + created.getCustomerID());

        Customer deleted = restTemplate.getForObject("/api/customer/" + created.getCustomerID(), Customer.class);
        assertThat(deleted).isNull();
    }

    @Test
    void getAllCustomersTest() {
        Customer[] customers = restTemplate.getForObject("/api/customer/all", Customer[].class);
        assertThat(customers).isNotNull();
    }
}

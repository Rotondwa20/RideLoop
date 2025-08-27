package za.co.rideloop.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Domain.CustomerRewards;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerRewardsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createRewardTest() {
        Customer customer = new Customer();
        customer.setCustomerID(1L); // Ensure this customer exists in DB

        CustomerRewards reward = new CustomerRewards();
        reward.setCustomer(customer);
        reward.setCurrentPoints(100);
        reward.setLifetimePoints(150);
        reward.setCurrentTier("Silver");
        reward.setLastActivityDate(new Date());

        CustomerRewards created = restTemplate.postForObject("/api/customerRewards/create", reward, CustomerRewards.class);

        assertThat(created).isNotNull();
        assertThat(created.getRewardID()).isNotNull();
        assertThat(created.getCurrentTier()).isEqualTo("Silver");
    }

    @Test
    void readRewardTest() {
        Customer customer = new Customer();
        customer.setCustomerID(1L);

        CustomerRewards reward = new CustomerRewards();
        reward.setCustomer(customer);
        reward.setCurrentPoints(50);
        reward.setCurrentTier("Bronze");

        CustomerRewards created = restTemplate.postForObject("/api/customerRewards/create", reward, CustomerRewards.class);
        Long id = created.getRewardID();

        CustomerRewards found = restTemplate.getForObject("/api/customerRewards/" + id, CustomerRewards.class);

        assertThat(found).isNotNull();
        assertThat(found.getCurrentPoints()).isEqualTo(50);
        assertThat(found.getCurrentTier()).isEqualTo("Bronze");
    }

    @Test
    void updateRewardTest() {
        Customer customer = new Customer();
        customer.setCustomerID(1L);

        CustomerRewards reward = new CustomerRewards();
        reward.setCustomer(customer);
        reward.setCurrentPoints(20);
        reward.setCurrentTier("Bronze");

        CustomerRewards created = restTemplate.postForObject("/api/customerRewards/create", reward, CustomerRewards.class);
        created.setCurrentPoints(70);
        created.setLifetimePoints(120);
        created.setCurrentTier("Gold");

        HttpEntity<CustomerRewards> entity = new HttpEntity<>(created);
        CustomerRewards updated = restTemplate.exchange("/api/customerRewards/update", HttpMethod.PUT, entity, CustomerRewards.class).getBody();

        assertThat(updated).isNotNull();
        assertThat(updated.getCurrentPoints()).isEqualTo(70);
        assertThat(updated.getLifetimePoints()).isEqualTo(120);
        assertThat(updated.getCurrentTier()).isEqualTo("Gold");
    }

    @Test
    void deleteRewardTest() {
        Customer customer = new Customer();
        customer.setCustomerID(1L);

        CustomerRewards reward = new CustomerRewards();
        reward.setCustomer(customer);
        reward.setCurrentPoints(10);

        CustomerRewards created = restTemplate.postForObject("/api/customerRewards/create", reward, CustomerRewards.class);
        Long id = created.getRewardID();

        restTemplate.delete("/api/customerRewards/" + id);

        CustomerRewards deleted = restTemplate.getForObject("/api/customerRewards/" + id, CustomerRewards.class);
        assertThat(deleted).isNull();
    }

    @Test
    void getAllRewardsTest() {
        CustomerRewards[] rewards = restTemplate.getForObject("/api/customerRewards/all", CustomerRewards[].class);
        assertThat(rewards).isNotNull();
    }
}

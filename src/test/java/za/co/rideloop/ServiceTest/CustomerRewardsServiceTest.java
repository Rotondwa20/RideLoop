package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Service.CustomerRewardsService;
import za.co.rideloop.Service.CustomerService;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerRewardsServiceTest {

    @Autowired
    private CustomerRewardsService rewardsService;

    @Autowired
    private CustomerService customerService;

    @Test
    void testCustomerRewardsCRUD() {

        Customer customer = new Customer.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john@example.com")
                .setPhone("1234567890")
                .setUsername("johndoe")
                .setPassword("12345")
                .setStatus("ACTIVE")
                .build();

        Customer savedCustomer = customerService.createCustomer(customer);
        assertNotNull(savedCustomer.getCustomerID(), "Customer ID should be generated");

        CustomerRewards reward = new CustomerRewards.Builder()
                .setCustomer(savedCustomer)
                .setCurrentPoints(100)
                .setLifetimePoints(500)
                .setCurrentTier("Silver")
                .setLastActivityDate(new Date())
                .build();

        CustomerRewards savedReward = rewardsService.create(reward);
        assertNotNull(savedReward.getRewardID(), "Reward ID should be generated");
        assertEquals("Silver", savedReward.getCurrentTier());


        CustomerRewards readReward = rewardsService.read(savedReward.getRewardID());
        assertEquals(100, readReward.getCurrentPoints());


        readReward.setCurrentPoints(150);
        readReward.setCurrentTier("Gold");
        CustomerRewards updatedReward = rewardsService.update(readReward);
        assertEquals(150, updatedReward.getCurrentPoints());
        assertEquals("Gold", updatedReward.getCurrentTier());


        List<CustomerRewards> allRewards = rewardsService.getAll();
        assertTrue(allRewards.size() >= 1, "There should be at least one reward");


        rewardsService.delete(updatedReward.getRewardID());
        assertThrows(RuntimeException.class,
                () -> rewardsService.read(updatedReward.getRewardID()),
                "Reading deleted reward should throw exception");
    }
}

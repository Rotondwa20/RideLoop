package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Service.CustomerRewardsService;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerRewardsServiceTest {

    @Autowired
    private CustomerRewardsService rewardsService;

    @Test
    void testCreateReward() {
        Customer customer = new Customer();
        customer.setCustomerID(1L);

        CustomerRewards reward = new CustomerRewards();
        reward.setCustomer(customer);
        reward.setCurrentPoints(100);
        reward.setLifetimePoints(150);
        reward.setCurrentTier("Silver");
        reward.setLastActivityDate(new Date());

        CustomerRewards saved = rewardsService.createReward(reward);

        assertNotNull(saved);
        assertNotNull(saved.getRewardID());
        assertEquals("Silver", saved.getCurrentTier());
        assertEquals(100, saved.getCurrentPoints());
    }

    @Test
    void testReadReward() {
        Customer customer = new Customer();
        customer.setCustomerID(1L);

        CustomerRewards reward = new CustomerRewards();
        reward.setCustomer(customer);
        reward.setCurrentPoints(50);
        reward.setLifetimePoints(80);
        reward.setCurrentTier("Bronze");
        reward.setLastActivityDate(new Date());

        CustomerRewards created = rewardsService.createReward(reward);
        Long id = created.getRewardID();

        CustomerRewards found = rewardsService.readReward(id);
        assertNotNull(found);
        assertEquals("Bronze", found.getCurrentTier());
        assertEquals(50, found.getCurrentPoints());
    }

    @Test
    void testUpdateReward() {
        Customer customer = new Customer();
        customer.setCustomerID(1L);

        CustomerRewards reward = new CustomerRewards();
        reward.setCustomer(customer);
        reward.setCurrentPoints(30);
        reward.setLifetimePoints(60);
        reward.setCurrentTier("Bronze");
        reward.setLastActivityDate(new Date());

        CustomerRewards created = rewardsService.createReward(reward);
        Long id = created.getRewardID();


        created.setCurrentPoints(70);
        created.setLifetimePoints(150);
        created.setCurrentTier("Gold");

        CustomerRewards updated = rewardsService.updateReward(created);

        assertEquals(70, updated.getCurrentPoints());
        assertEquals(150, updated.getLifetimePoints());
        assertEquals("Gold", updated.getCurrentTier());
    }

    @Test
    void testDeleteReward() {
        Customer customer = new Customer();
        customer.setCustomerID(1L);

        CustomerRewards reward = new CustomerRewards();
        reward.setCustomer(customer);
        reward.setCurrentPoints(10);
        reward.setLifetimePoints(20);
        reward.setCurrentTier("Bronze");
        reward.setLastActivityDate(new Date());

        CustomerRewards created = rewardsService.createReward(reward);
        Long id = created.getRewardID();

        rewardsService.deleteReward(id);

        CustomerRewards deleted = rewardsService.readReward(id);
        assertNull(deleted);
    }

    @Test
    void testGetAllRewards() {
        List<CustomerRewards> rewards = rewardsService.getAllRewards();
        assertNotNull(rewards);
    }
}

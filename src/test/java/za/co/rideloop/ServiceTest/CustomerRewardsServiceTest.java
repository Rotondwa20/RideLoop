package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Repository.UserRepository;
import za.co.rideloop.Service.CustomerRewardsService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerRewardsServiceTest {

    @Autowired
    private CustomerRewardsService rewardsService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private CustomerRewards testReward;

    @BeforeEach
    void setup() {
        // Create and save a user
        testUser = new User.Builder()
                .setUsername("testuser")
                .setEmail("test@example.com")
                .setPassword("password")
                .build();

        testUser = userRepository.save(testUser); // persist user

        // Create a reward linked to the persisted user
        testReward = new CustomerRewards.Builder()
                .setUser(testUser)
                .setCurrentPoints(100)
                .setLifetimePoints(150)
                .setCurrentTier("Silver")
                .setLastActivityDate(new Date())
                .build();

        testReward = rewardsService.createReward(testReward); // persist reward
    }

    @Test
    void testCreateReward() {
        assertNotNull(testReward);
        assertNotNull(testReward.getRewardID());
        assertEquals("Silver", testReward.getCurrentTier());
        assertEquals(100, testReward.getCurrentPoints());
        assertEquals(testUser.getUsername(), testReward.getUser().getUsername());
    }

    @Test
    void testReadReward() {
        CustomerRewards found = rewardsService.readReward(testReward.getRewardID());
        assertNotNull(found);
        assertEquals("Silver", found.getCurrentTier());
    }

    @Test
    void testUpdateReward() {
        testReward.setCurrentPoints(200);
        testReward.setCurrentTier("Gold");

        CustomerRewards updated = rewardsService.updateReward(testReward);

        assertEquals(200, updated.getCurrentPoints());
        assertEquals("Gold", updated.getCurrentTier());
    }

    @Test
    void testDeleteReward() {
        rewardsService.deleteReward(testReward.getRewardID());

        CustomerRewards deleted = rewardsService.readReward(testReward.getRewardID());
        assertNull(deleted);
    }

}
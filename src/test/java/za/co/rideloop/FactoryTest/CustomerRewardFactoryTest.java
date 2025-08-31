package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Factory.CustomerRewardFactory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerRewardFactoryTest {

    @Test
    void testCreateCustomerRewardsWithDefaults() {
        User user = new User.Builder()
                .setEmail("tondy@example.com")
                .setPassword("password")
                .build();

        CustomerRewards rewards = CustomerRewardFactory.createCustomerRewards(user);

        assertNotNull(rewards);
        assertEquals(user, rewards.getUser());
        assertEquals(0, rewards.getCurrentPoints());
        assertEquals(0, rewards.getLifetimePoints());
        assertEquals("Bronze", rewards.getCurrentTier());
        assertNotNull(rewards.getLastActivityDate());
        assertEquals(0, rewards.getRewardID()); // default int before saving to DB
    }

    @Test
    void testCreateFullCustomerRewards() {
        User user = new User.Builder()
                .setEmail("alice@example.com")
                .setPassword("securePass")
                .build();

        Date now = new Date();

        CustomerRewards rewards = CustomerRewardFactory.createFullCustomerRewards(
                user,
                150,
                500,
                "Silver",
                now
        );

        assertNotNull(rewards);
        assertEquals(user, rewards.getUser());
        assertEquals(150, rewards.getCurrentPoints());
        assertEquals(500, rewards.getLifetimePoints());
        assertEquals("Silver", rewards.getCurrentTier());
        assertEquals(now, rewards.getLastActivityDate());
        assertEquals(0, rewards.getRewardID()); // default int before saving to DB
    }
}

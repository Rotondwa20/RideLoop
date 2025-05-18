package za.co.rideloop.FactoryTest;

/**
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */

import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Factory.CustomerRewardFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;


public class CustomerRewardFactoryTest {

    @Test
    public void testCreateDefault() {
        CustomerRewards reward = CustomerRewardFactory.createDefault();

        assertNotNull(reward);
        assertEquals(0, reward.getCustomerID());
        assertEquals(0, reward.getCurrentPoints());
        assertEquals(0, reward.getLifetimePoints());
        assertEquals("Bronze", reward.getCurrentTier());
        assertNotNull(reward.getLastActivityDate());
    }

    @Test
    public void testCreateWithParameters() {
        Date testDate = new Date();
        CustomerRewards reward = CustomerRewardFactory.create(123, 100, 500, "Gold", testDate);

        assertNotNull(reward);
        assertEquals(123, reward.getCustomerID());
        assertEquals(100, reward.getCurrentPoints());
        assertEquals(500, reward.getLifetimePoints());
        assertEquals("Gold", reward.getCurrentTier());
        assertEquals(testDate, reward.getLastActivityDate());
    }

    @Test
    public void testCreateWithNullDate() {
        CustomerRewards reward = CustomerRewardFactory.create(123, 100, 500, "Gold", null);

        assertNotNull(reward);
        assertNotNull(reward.getLastActivityDate());
    }

    @Test
    public void testCreateNewCustomer() {
        CustomerRewards reward = CustomerRewardFactory.createNewCustomer(456);

        assertNotNull(reward);
        assertEquals(456, reward.getCustomerID());
        assertEquals(0, reward.getCurrentPoints());
        assertEquals(0, reward.getLifetimePoints());
        assertEquals("Bronze", reward.getCurrentTier());
        assertNotNull(reward.getLastActivityDate());
    }

    @Test
    public void testCopyNonNull() {
        Date testDate = new Date();
        CustomerRewards original = CustomerRewardFactory.create(789, 200, 1000, "Silver", testDate);
        CustomerRewards copy = CustomerRewardFactory.copy(original);

        assertNotNull(copy);
        assertEquals(original.getCustomerID(), copy.getCustomerID());
        assertEquals(original.getCurrentPoints(), copy.getCurrentPoints());
        assertEquals(original.getLifetimePoints(), copy.getLifetimePoints());
        assertEquals(original.getCurrentTier(), copy.getCurrentTier());
        assertEquals(original.getLastActivityDate(), copy.getLastActivityDate());
        assertNotSame(original, copy); // Ensure it's a copy, not the same reference
    }

    @Test
    public void testCopyNull() {
        CustomerRewards copy = CustomerRewardFactory.copy(null);

        assertNotNull(copy);
        assertEquals(0, copy.getCustomerID());
        assertEquals(0, copy.getCurrentPoints());
        assertEquals(0, copy.getLifetimePoints());
        assertEquals("Bronze", copy.getCurrentTier());
        assertNotNull(copy.getLastActivityDate());
    }
}
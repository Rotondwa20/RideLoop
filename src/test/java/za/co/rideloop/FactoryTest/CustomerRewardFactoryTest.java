package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Factory.CustomerRewardFactory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRewardFactoryTest {

    @Test
    public void testCreateDefault() {
        CustomerRewards rewards = CustomerRewardFactory.createDefault(null);

        assertNotNull(rewards, "CustomerRewards should not be null");
        assertNull(rewards.getCustomer(), "Customer should be null in default reward");
        assertEquals(0, rewards.getCurrentPoints(), "Default currentPoints should be 0");
        assertEquals(0, rewards.getLifetimePoints(), "Default lifetimePoints should be 0");
        assertEquals("Bronze", rewards.getCurrentTier(), "Default tier should be Bronze");
        assertNotNull(rewards.getLastActivityDate(), "LastActivityDate should be set");
    }

    @Test
    public void testCreateNewCustomer() {
        Customer customer = new Customer.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .build();

        CustomerRewards rewards = CustomerRewardFactory.createNewCustomer(customer);

        assertNotNull(rewards);
        assertEquals(customer, rewards.getCustomer());
        assertEquals(0, rewards.getCurrentPoints());
        assertEquals(0, rewards.getLifetimePoints());
        assertEquals("Bronze", rewards.getCurrentTier());
        assertNotNull(rewards.getLastActivityDate());
    }

    @Test
    public void testCreateCustomReward() {
        Customer customer = new Customer.Builder()
                .setFirstName("Jane")
                .setLastName("Smith")
                .build();

        Date date = new Date();
        CustomerRewards rewards = CustomerRewardFactory.create(
                customer,
                100,
                500,
                "Gold",
                date
        );

        assertNotNull(rewards);
        assertEquals(customer, rewards.getCustomer());
        assertEquals(100, rewards.getCurrentPoints());
        assertEquals(500, rewards.getLifetimePoints());
        assertEquals("Gold", rewards.getCurrentTier());
        assertEquals(date, rewards.getLastActivityDate());
    }

    @Test
    public void testCopyCustomerRewards() {
        Customer customer = new Customer.Builder()
                .setFirstName("Alice")
                .setLastName("Johnson")
                .build();

        Date date = new Date();
        CustomerRewards original = CustomerRewardFactory.create(customer, 50, 100, "Silver", date);
        CustomerRewards copy = CustomerRewardFactory.copy(original);

        assertNotNull(copy);
        assertEquals(original.getCustomer(), copy.getCustomer());
        assertEquals(original.getCurrentPoints(), copy.getCurrentPoints());
        assertEquals(original.getLifetimePoints(), copy.getLifetimePoints());
        assertEquals(original.getCurrentTier(), copy.getCurrentTier());
        assertEquals(original.getLastActivityDate(), copy.getLastActivityDate());
        assertNotSame(original, copy, "Copy should be a new object, not the same reference");
    }
}

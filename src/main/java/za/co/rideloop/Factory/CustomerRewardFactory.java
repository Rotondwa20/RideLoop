package za.co.rideloop.Factory;

import java.util.Date;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Domain.CustomerRewards;

/**
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */
public class CustomerRewardFactory {


    public static CustomerRewards createDefault(Customer customer) {
        return create(customer, 0, 0, "Bronze", new Date());
    }

    public static CustomerRewards create(Customer customer, int currentPoints,
                                         int lifetimePoints, String currentTier,
                                         Date lastActivityDate) {
        if (lastActivityDate == null) lastActivityDate = new Date();

        return new CustomerRewards.Builder()
                .setCustomer(customer)
                .setCurrentPoints(currentPoints)
                .setLifetimePoints(lifetimePoints)
                .setCurrentTier(currentTier)
                .setLastActivityDate(lastActivityDate)
                .build();
    }


    public static CustomerRewards createNewCustomer(Customer customer) {
        return create(customer, 0, 0, "Bronze", new Date());
    }


    public static CustomerRewards copy(CustomerRewards original) {
        if (original == null) return createDefault(null);
        return create(
                original.getCustomer(),
                original.getCurrentPoints(),
                original.getLifetimePoints(),
                original.getCurrentTier(),
                original.getLastActivityDate()
        );
    }
}

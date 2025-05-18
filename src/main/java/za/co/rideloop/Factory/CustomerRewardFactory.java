package za.co.rideloop.Factory;

import java.util.Date;
import za.co.rideloop.Domain.CustomerRewards;

/**
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */

public class CustomerRewardFactory {

    public static CustomerRewards createDefault() {
        return create(0, 0, 0, "Bronze", new Date());
    }

    public static CustomerRewards create(int customerID, int currentPoints,
                                         int lifetimePoints, String currentTier,
                                         Date lastActivityDate) {
        return new CustomerRewards.Builder()
                .setCustomerID(customerID)
                .setCurrentPoints(currentPoints)
                .setLifetimePoints(lifetimePoints)
                .setCurrentTier(currentTier)
                .setLastActivityDate(lastActivityDate != null ? lastActivityDate : new Date())
                .build();
    }

    public static CustomerRewards createNewCustomer(int customerID) {
        return create(customerID, 0, 0, "Bronze", new Date());
    }

    public static CustomerRewards copy(CustomerRewards original) {
        if (original == null) return createDefault();
        return create(
                original.getCustomerID(),
                original.getCurrentPoints(),
                original.getLifetimePoints(),
                original.getCurrentTier(),
                original.getLastActivityDate()
        );
    }
}
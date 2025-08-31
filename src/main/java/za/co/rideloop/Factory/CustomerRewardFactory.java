package za.co.rideloop.Factory;

import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Domain.User;

import java.util.Date;

/**
 * Factory class for creating CustomerRewards objects linked to User.
 *
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */
public class CustomerRewardFactory {

    /**
     * Create a basic CustomerRewards object with default points, tier, and current date.
     *
     * @param user The user associated with the reward
     * @return CustomerRewards instance
     */
    public static CustomerRewards createCustomerRewards(User user) {
        return new CustomerRewards.Builder()
                .setUser(user)
                .setCurrentPoints(0)
                .setLifetimePoints(0)
                .setCurrentTier("Bronze")
                .setLastActivityDate(new Date())
                .build();
    }

    /**
     * Create a full CustomerRewards object with custom points, tier, and last activity date.
     *
     * @param user The user associated with the reward
     * @param currentPoints Current reward points
     * @param lifetimePoints Total lifetime points
     * @param currentTier Customer tier
     * @param lastActivityDate Last activity date
     * @return CustomerRewards instance
     */
    public static CustomerRewards createFullCustomerRewards(User user,
                                                            int currentPoints,
                                                            int lifetimePoints,
                                                            String currentTier,
                                                            Date lastActivityDate) {
        return new CustomerRewards.Builder()
                .setUser(user)
                .setCurrentPoints(currentPoints)
                .setLifetimePoints(lifetimePoints)
                .setCurrentTier(currentTier)
                .setLastActivityDate(lastActivityDate)
                .build();
    }
}

package za.co.rideloop.Factory;

import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Domain.Payment;

import java.time.LocalDateTime;


public class CustomerRewardsFactory {

    public static CustomerRewards createReward(CustomerProfile profile, Payment payment) {
        return new CustomerRewards.Builder()
                .setCustomerProfile(profile)
                .setPayment(payment)
                .setCurrentPoints(0)
                .setLifetimePoints(0)
                .setCurrentTier("Bronze")
                .setLastActivityDate(LocalDateTime.now())
                .build();
    }


    public static CustomerRewards createReward(CustomerProfile profile, Payment payment,
                                               int currentPoints, int lifetimePoints, String tier) {
        return new CustomerRewards.Builder()
                .setCustomerProfile(profile)
                .setPayment(payment)
                .setCurrentPoints(currentPoints)
                .setLifetimePoints(lifetimePoints)
                .setCurrentTier(tier)
                .setLastActivityDate(LocalDateTime.now())
                .build();
    }
}

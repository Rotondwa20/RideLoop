package za.co.rideloop.Service;

import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Repository.CustomerRewardsRepository;
import za.co.rideloop.Repository.CustomerProfileRepository;
import za.co.rideloop.Repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerRewardsService {

    private final CustomerRewardsRepository rewardsRepo;
    private final CustomerProfileRepository profileRepo;
    private final PaymentRepository paymentRepo;

    public CustomerRewardsService(CustomerRewardsRepository rewardsRepo,
                                  CustomerProfileRepository profileRepo,
                                  PaymentRepository paymentRepo) {
        this.rewardsRepo = rewardsRepo;
        this.profileRepo = profileRepo;
        this.paymentRepo = paymentRepo;
    }

    /**
     * Process a payment and update reward points for the customer.
     */
    public CustomerRewards processPaymentRewards(Integer paymentId, Integer profileId) {
        if (paymentId == null || profileId == null) {
            throw new IllegalArgumentException("Payment ID and Profile ID cannot be null");
        }

        Optional<Payment> paymentOpt = paymentRepo.findById(paymentId);
        Optional<CustomerProfile> profileOpt = profileRepo.findById(profileId);

        if (paymentOpt.isEmpty() || profileOpt.isEmpty()) {
            throw new IllegalArgumentException("Payment or Profile not found!");
        }

        Payment payment = paymentOpt.get();
        CustomerProfile profile = profileOpt.get();

        // Calculate reward points: 1 point per R10 spent
        int earnedPoints = (payment.getPaymentAmount() != null) ? (int) Math.floor(payment.getPaymentAmount() / 10) : 0;

        // Retrieve existing rewards or create new
        CustomerRewards rewards = rewardsRepo.findByCustomerProfile(profile)
                .orElse(new CustomerRewards.Builder()
                        .setCustomerProfile(profile)
                        .setPayment(payment)
                        .build());

        // Update points
        rewards.setCurrentPoints(rewards.getCurrentPoints() + earnedPoints);
        rewards.setLifetimePoints(rewards.getLifetimePoints() + earnedPoints);
        rewards.setLastActivityDate(LocalDateTime.now());

        // Update tier
        rewards.setCurrentTier(determineTier(rewards.getLifetimePoints()));

        return rewardsRepo.save(rewards);
    }

    /**
     * Get rewards for a profile by ID.
     */
    public Optional<CustomerRewards> getRewardsByProfileId(Integer profileId) {
        if (profileId == null) return Optional.empty();
        return profileRepo.findById(profileId)
                .flatMap(rewardsRepo::findByCustomerProfile);
    }

    /**
     * Determine tier based on lifetime points.
     */
    private String determineTier(int lifetimePoints) {
        if (lifetimePoints >= 1000) return "Platinum";
        if (lifetimePoints >= 500) return "Gold";
        if (lifetimePoints >= 250) return "Silver";
        return "Bronze";
    }
}

package za.co.rideloop.Service;

import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Repository.CustomerProfileRepository;
import za.co.rideloop.Repository.CustomerRewardsRepository;
import za.co.rideloop.Repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.List;

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

    /** 🎁 Each payment creates a new CustomerRewards record */
    public CustomerRewards processPaymentRewards(Long paymentId, Integer profileId) {
        if (paymentId == null || profileId == null) {
            throw new IllegalArgumentException("Payment ID and Profile ID cannot be null");
        }

        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        CustomerProfile profile = profileRepo.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Customer profile not found"));

        // 1 point per R10 spent
        double amount = payment.getPaymentAmount() != null ? payment.getPaymentAmount() : 0;
        int earnedPoints = (int) Math.floor(amount / 10);

        // ✅ Always create a new rewards record
        CustomerRewards newReward = new CustomerRewards.Builder()
                .setCustomerProfile(profile)
                .setPayment(payment)
                .setCurrentPoints(earnedPoints)
                .setLifetimePoints(earnedPoints)
                .setCurrentTier(determineTier(earnedPoints))
                .setLastActivityDate(LocalDateTime.now())
                .build();

        return rewardsRepo.save(newReward);
    }

    /** Get all rewards for a customer */
    public List<CustomerRewards> getAllRewardsByProfileId(Integer profileId) {
        return rewardsRepo.findAllByCustomerProfile_ProfileID(profileId);
    }

    /** Calculate total points across all rewards */
    public int getTotalPointsForProfile(Integer profileId) {
        return getAllRewardsByProfileId(profileId).stream()
                .mapToInt(CustomerRewards::getCurrentPoints)
                .sum();
    }

    /** Determine current tier based on points */
    public String getTierForProfile(Integer profileId) {
        int totalPoints = getTotalPointsForProfile(profileId);
        return determineTier(totalPoints);
    }

    private String determineTier(int points) {
        if (points >= 1000) return "Platinum";
        if (points >= 500) return "Gold";
        if (points >= 250) return "Silver";
        return "Bronze";
    }
}

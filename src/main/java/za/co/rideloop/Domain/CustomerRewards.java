package za.co.rideloop.Domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * CustomerRewards.java
 * Customer loyalty model class (JPA entity)
 *
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */
@Entity
@Table(name = "customer_rewards")
public class CustomerRewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rewardid")
    private Long rewardID; // now using Long

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private CustomerProfile customerProfile;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId")
    private Payment payment;

    private int currentPoints;
    private int lifetimePoints;
    private String currentTier;

    private LocalDateTime lastActivityDate; // modern date/time API

    public CustomerRewards() {}

    // ---------- Getters and Setters ----------
    public Long getRewardID() { return rewardID; }
    public void setRewardID(Long rewardID) { this.rewardID = rewardID; }

    public CustomerProfile getCustomerProfile() { return customerProfile; }
    public void setCustomerProfile(CustomerProfile customerProfile) { this.customerProfile = customerProfile; }

    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public int getCurrentPoints() { return currentPoints; }
    public void setCurrentPoints(int currentPoints) { this.currentPoints = currentPoints; }

    public int getLifetimePoints() { return lifetimePoints; }
    public void setLifetimePoints(int lifetimePoints) { this.lifetimePoints = lifetimePoints; }

    public String getCurrentTier() { return currentTier; }
    public void setCurrentTier(String currentTier) { this.currentTier = currentTier; }

    public LocalDateTime getLastActivityDate() { return lastActivityDate; }
    public void setLastActivityDate(LocalDateTime lastActivityDate) { this.lastActivityDate = lastActivityDate; }

    // ---------- Builder pattern ----------
    public static class Builder {
        private CustomerProfile customerProfile;
        private Payment payment;
        private int currentPoints = 0;
        private int lifetimePoints = 0;
        private String currentTier = "Bronze"; // default tier
        private LocalDateTime lastActivityDate = LocalDateTime.now(); // default now

        public Builder setCustomerProfile(CustomerProfile customerProfile) {
            this.customerProfile = customerProfile;
            return this;
        }

        public Builder setPayment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder setCurrentPoints(int currentPoints) {
            this.currentPoints = currentPoints;
            return this;
        }

        public Builder setLifetimePoints(int lifetimePoints) {
            this.lifetimePoints = lifetimePoints;
            return this;
        }

        public Builder setCurrentTier(String currentTier) {
            this.currentTier = currentTier;
            return this;
        }

        public Builder setLastActivityDate(LocalDateTime lastActivityDate) {
            this.lastActivityDate = lastActivityDate;
            return this;
        }

        public CustomerRewards build() {
            CustomerRewards reward = new CustomerRewards();
            reward.setCustomerProfile(customerProfile);
            reward.setPayment(payment);
            reward.setCurrentPoints(currentPoints);
            reward.setLifetimePoints(lifetimePoints);
            reward.setCurrentTier(currentTier);
            reward.setLastActivityDate(lastActivityDate);
            return reward;
        }
    }
}

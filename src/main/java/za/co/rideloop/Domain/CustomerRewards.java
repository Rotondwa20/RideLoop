package za.co.rideloop.Domain;

import jakarta.persistence.*;

import java.util.Date;

/**
 * CustomerRewards.java
 * Customer loyalty model class (JPA entity) linked directly to User.
 *
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */
@Entity
public class CustomerRewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rewardID; // using int

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int currentPoints;
    private int lifetimePoints;
    private String currentTier;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivityDate;

    public CustomerRewards() {}

    // Getters and Setters
    public int getRewardID() {
        return rewardID;
    }

    public void setRewardID(int rewardID) {
        this.rewardID = rewardID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getLifetimePoints() {
        return lifetimePoints;
    }

    public void setLifetimePoints(int lifetimePoints) {
        this.lifetimePoints = lifetimePoints;
    }

    public String getCurrentTier() {
        return currentTier;
    }

    public void setCurrentTier(String currentTier) {
        this.currentTier = currentTier;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    // Builder pattern for easy creation
    public static class Builder {
        private User user;
        private int currentPoints = 0;
        private int lifetimePoints = 0;
        private String currentTier = "Bronze"; // default tier
        private Date lastActivityDate = new Date(); // default now

        public Builder setUser(User user) {
            this.user = user;
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

        public Builder setLastActivityDate(Date lastActivityDate) {
            this.lastActivityDate = lastActivityDate;
            return this;
        }

        public CustomerRewards build() {
            CustomerRewards reward = new CustomerRewards();
            reward.setUser(user);
            reward.setCurrentPoints(currentPoints);
            reward.setLifetimePoints(lifetimePoints);
            reward.setCurrentTier(currentTier);
            reward.setLastActivityDate(lastActivityDate);
            return reward;
        }
    }
}

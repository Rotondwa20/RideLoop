package za.co.rideloop.Domain;


import java.util.Date;

/**
 * CustomerRewards.java
 * Customer loyalty model class
 *
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */

public class CustomerRewards {
    private int customerID;
    private int currentPoints;
    private int lifetimePoints;
    private String currentTier;
    private Date lastActivityDate;

    public CustomerRewards() {
    }

    public CustomerRewards(Builder builder) {
        this.customerID = builder.customerID;
        this.currentPoints = builder.currentPoints;
        this.lifetimePoints = builder.lifetimePoints;
        this.currentTier = builder.currentTier;
        this.lastActivityDate = builder.lastActivityDate;
    }

    // Getters
    public int getCustomerID() {
        return customerID;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public int getLifetimePoints() {
        return lifetimePoints;
    }

    public String getCurrentTier() {
        return currentTier;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    // Builder Class
    public static class Builder {
        private int customerID;
        private int currentPoints;
        private int lifetimePoints;
        private String currentTier;
        private Date lastActivityDate;

        public Builder setCustomerID(int customerID) {
            this.customerID = customerID;
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
            return new CustomerRewards(this);
        }
    }
}

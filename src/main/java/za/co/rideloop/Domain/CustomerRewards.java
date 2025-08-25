package za.co.rideloop.Domain;

import jakarta.persistence.*;
import java.util.Date;

/**
 * CustomerRewards.java
 * Customer loyalty model class (JPA entity)
 *
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */
@Entity

public class CustomerRewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardID;

    @ManyToOne
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

    private int currentPoints;
    private int lifetimePoints;
    private String currentTier;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivityDate;

    public CustomerRewards() {}

    private CustomerRewards(Builder builder) {
        this.customer = builder.customer;
        this.currentPoints = builder.currentPoints;
        this.lifetimePoints = builder.lifetimePoints;
        this.currentTier = builder.currentTier != null ? builder.currentTier : "Bronze"; // default
        this.lastActivityDate = builder.lastActivityDate != null ? builder.lastActivityDate : new Date();
    }

    // Setters
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setCurrentPoints(int currentPoints) { this.currentPoints = currentPoints; }
    public void setLifetimePoints(int lifetimePoints) { this.lifetimePoints = lifetimePoints; }
    public void setCurrentTier(String currentTier) { this.currentTier = currentTier; }
    public void setLastActivityDate(Date lastActivityDate) { this.lastActivityDate = lastActivityDate; }

    // Getters
    public Long getRewardID() { return rewardID; }
    public Customer getCustomer() { return customer; }
    public int getCurrentPoints() { return currentPoints; }
    public int getLifetimePoints() { return lifetimePoints; }
    public String getCurrentTier() { return currentTier; }
    public Date getLastActivityDate() { return lastActivityDate; }

    // Builder
    public static class Builder {
        private Customer customer;
        private int currentPoints = 0;
        private int lifetimePoints = 0;
        private String currentTier = "Bronze"; // default
        private Date lastActivityDate = new Date(); // default

        public Builder setCustomer(Customer customer) { this.customer = customer; return this; }
        public Builder setCurrentPoints(int currentPoints) { this.currentPoints = currentPoints; return this; }
        public Builder setLifetimePoints(int lifetimePoints) { this.lifetimePoints = lifetimePoints; return this; }
        public Builder setCurrentTier(String currentTier) { this.currentTier = currentTier; return this; }
        public Builder setLastActivityDate(Date lastActivityDate) { this.lastActivityDate = lastActivityDate; return this; }

        public CustomerRewards build() { return new CustomerRewards(this); }
    }
}

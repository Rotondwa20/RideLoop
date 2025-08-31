package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Repository.CustomerRewardsRepository;

import java.util.List;

/**
 * Service class for managing CustomerRewards.
 *
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */
@Service
public class CustomerRewardsService {

    @Autowired
    private CustomerRewardsRepository repository;

    // Create a new reward
    public CustomerRewards createReward(CustomerRewards reward) {
        return this.repository.save(reward);
    }

    // Read a reward by ID
    public CustomerRewards readReward(int id) {
        return this.repository.findById(id).orElse(null);
    }

    // Update a reward
    public CustomerRewards updateReward(CustomerRewards reward) {
        return this.repository.save(reward);
    }

    // Delete a reward by ID
    public void deleteReward(int id) {
        this.repository.deleteById(id);
    }

    // Get all rewards
    public List<CustomerRewards> getAllRewards() {
        return this.repository.findAll();
    }
}

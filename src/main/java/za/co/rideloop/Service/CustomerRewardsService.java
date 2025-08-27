package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Repository.CustomerRewardsRepository;

import java.util.List;


@Service
public class CustomerRewardsService {

    @Autowired
    private CustomerRewardsRepository repository;


    public CustomerRewards createReward(CustomerRewards reward) {
        return this.repository.save(reward);
    }

    public CustomerRewards readReward(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public CustomerRewards updateReward(CustomerRewards reward) {
        return this.repository.save(reward);
    }

    public void deleteReward(Long id) {
        this.repository.deleteById(id);
    }


    public List<CustomerRewards> getAllRewards() {
        return this.repository.findAll();
    }

}

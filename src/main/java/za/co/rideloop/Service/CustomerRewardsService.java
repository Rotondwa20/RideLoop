package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Repository.CustomerRewardsRepository;
import za.co.rideloop.Service.Imp.ICustomerRewardsService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerRewardsService implements ICustomerRewardsService {

    private final CustomerRewardsRepository repository;

    @Autowired
    public CustomerRewardsService(CustomerRewardsRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerRewards create(CustomerRewards customerRewards) {
        return repository.save(customerRewards);
    }

    @Override
    public CustomerRewards read(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerReward not found with id: " + id));
    }

    @Override
    public CustomerRewards update(CustomerRewards customerRewards) {
        Long rewardId = customerRewards.getRewardID();
        if (rewardId == null || !repository.existsById(rewardId)) {
            throw new RuntimeException("Cannot update — CustomerReward not found with id: " + rewardId);
        }
        return repository.save(customerRewards);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cannot delete — CustomerReward not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<CustomerRewards> getAll() {
        return repository.findAll();
    }

    public Optional<CustomerRewards> getRewardById(Long id) {
        return repository.findById(id);
    }

    public CustomerRewards updateReward(Long id, CustomerRewards updatedReward) {
        CustomerRewards existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot update — CustomerReward not found with id: " + id));

        existing.setCustomer(updatedReward.getCustomer());
        existing.setCurrentPoints(updatedReward.getCurrentPoints());
        existing.setLifetimePoints(updatedReward.getLifetimePoints());
        existing.setCurrentTier(updatedReward.getCurrentTier());
        existing.setLastActivityDate(updatedReward.getLastActivityDate());

        return repository.save(existing);
    }
}

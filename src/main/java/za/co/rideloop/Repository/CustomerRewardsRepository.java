package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Domain.CustomerProfile;

import java.util.Optional;

public interface CustomerRewardsRepository extends JpaRepository<CustomerRewards, Long> {
    Optional<CustomerRewards> findByCustomerProfile(CustomerProfile profile);
}

package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.rideloop.Domain.CustomerRewards;

import java.util.List;
import java.util.Optional;

public interface CustomerRewardsRepository extends JpaRepository<CustomerRewards, Long> {

    // Use the entity field name 'profileID'

    List<CustomerRewards> findAllByCustomerProfile_ProfileID(Integer profileId);
}

package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.CustomerRewards;

import java.util.List;


@Repository
public interface CustomerRewardsRepository extends JpaRepository<CustomerRewards, Long> {



}

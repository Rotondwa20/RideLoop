package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.CustomerRewards;

@Repository
public interface CustomerRewardsRepository extends JpaRepository<CustomerRewards, Integer> {
    void deleteByUserUserID(int userID);

}

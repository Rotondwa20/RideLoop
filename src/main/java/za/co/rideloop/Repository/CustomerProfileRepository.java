package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.CustomerProfile;

import java.util.Optional;
import java.util.List;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Integer> {
    Optional<CustomerProfile> findByUser_Id(int userId);
    List<CustomerProfile> findByStatusIgnoreCase(String status);

}

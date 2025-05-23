package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.Location;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location , Integer> {

    Optional<Location> findByStreetName(String streetName);
}

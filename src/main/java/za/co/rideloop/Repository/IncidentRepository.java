package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.Incident;
import java.util.List;


public interface IncidentRepository extends JpaRepository<Incident, Integer> {

    List<Incident> findByProfile(CustomerProfile profile);

}


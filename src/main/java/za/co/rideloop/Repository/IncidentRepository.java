package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.rideloop.Domain.Incident;

/**
 * RideLoop
 * IncidentRepository.java
 * <p>
 * author : Swatsi Bongani Ratia
 * studnr : 230724477
 * group : 3I
 * date : 5/24/2025
 * Java version: "21.0.3" 2024-04-16 LTS
 */
public interface IncidentRepository extends JpaRepository<Incident, Integer> {

}

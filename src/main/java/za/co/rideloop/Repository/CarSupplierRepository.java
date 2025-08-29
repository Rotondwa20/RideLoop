package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.CarSupplier;

/**
 * RideLoop
 * CarSupplier.java
 * <p>
 * author : Swatsi Bongani Ratia
 * studnr : 230724477
 * group : 3I
 * date : 5/24/2025
 * Java version: "21.0.3" 2024-04-16 LTS
 */
@Repository
public interface CarSupplierRepository extends JpaRepository<CarSupplier, Integer> {

}

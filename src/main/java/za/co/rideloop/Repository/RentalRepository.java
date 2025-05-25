package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.rideloop.Domain.Rental;

public interface RentalRepository  extends JpaRepository<Rental,Integer > {

}

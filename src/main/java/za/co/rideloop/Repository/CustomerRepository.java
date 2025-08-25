package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

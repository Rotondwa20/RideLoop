package za.co.rideloop.Repository;
/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 **/
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.rideloop.Domain.Payment;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {


}

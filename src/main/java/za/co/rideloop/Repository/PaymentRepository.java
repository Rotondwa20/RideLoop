package za.co.rideloop.Repository;
/**
 * InvoiceRepository.java
 * InvoiceRepository Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 **/

/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Ndyebo Qole
 * @Student Number: 210018615
 * Group 3 I
 **/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Domain.Rental;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {



    List<Payment> findByRental(Rental rental);

    List<Payment> findByPaymentStatus(String status);

    List<Payment> findByPaymentMethod(String method);
}

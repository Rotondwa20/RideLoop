package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Domain.Rental;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByRental(Rental rental);

    List<Payment> findByPaymentStatus(String status);

    List<Payment> findByPaymentMethod(String method);

    Optional<Payment> findById(Long paymentId);
}

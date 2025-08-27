package za.co.rideloop.Repository;
/**
 * InvoiceRepository.java
 * InvoiceRepository Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 *  group 3I
 **/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.Invoice;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
  //  Optional<Invoice> findByPaymentReference(String paymentReference);
   // Invoice findByPaymentReference(String paymentReference);
  Optional<Invoice> findByPaymentReference(String paymentReference);

  @Override
  List<Invoice> findAllById(Iterable<Integer> integers);

}

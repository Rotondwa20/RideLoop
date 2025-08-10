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
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {


}

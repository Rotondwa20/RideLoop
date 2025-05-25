package za.co.rideloop.Repository;
/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 *  group 3I
 **/
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.rideloop.Domain.Invoice;
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}

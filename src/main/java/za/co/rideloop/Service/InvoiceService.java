package za.co.rideloop.Service;
/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Repository.InvoiceRepository;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService{

    @Autowired
    private InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }




    public Invoice create(Invoice invoice) {

            return repository.save(invoice);

    }


    public Invoice read(Integer invoiceID) {
        return repository.findById(invoiceID).orElse(null);
    }


    public Invoice update(Invoice invoice) {

            // Check if the input invoice is valid and has an ID
            if (invoice == null || invoice.getInvoiceID() == 0) {
                return null;
            }

            // Find the existing invoice by its ID
            Optional<Invoice> existingInvoiceOpt = repository.findById(invoice.getInvoiceID());

            if (existingInvoiceOpt.isPresent()) {
                Invoice existingInvoice = existingInvoiceOpt.get();

                // Use the builder to create a new updated object.
                // This is a robust way to handle updates when your entity doesn't have public setters.
                Invoice updatedInvoice = new Invoice.InvoiceBuilder()
                        .setInvoiceID(existingInvoice.getInvoiceID()) // Keep the original ID
                        .setInvoiceDate(invoice.getInvoiceDate())
                        .setDueDate(invoice.getDueDate())
                        .setStatus(invoice.getStatus())
                        .setSubtotal(invoice.getSubtotal())
                        .setTaxAmount(invoice.getTaxAmount())
                        .setDiscountAmount(invoice.getDiscountAmount())
                        .setTotalAmount(invoice.getTotalAmount())
                        .setPaymentMethod(invoice.getPaymentMethod())
                        .setPaymentReference(invoice.getPaymentReference())
                        .build();

                return repository.save(updatedInvoice);
            }
            return null; // Not found

    }

    public List<Invoice> getAll() {
        return repository.findAll();
    }




    public Invoice getInvoiceByPaymentReference(String paymentReference) {
        return repository.findByPaymentReference(paymentReference).orElse(null);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
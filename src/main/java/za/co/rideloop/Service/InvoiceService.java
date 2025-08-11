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
import za.co.rideloop.util.Helper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Repository.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public Invoice createInvoice(Invoice invoice) {
        return repository.save(invoice);
    }

    public Invoice readInvoice(int id) {
        return repository.findById(id).orElse(null);
    }

    public Invoice updateInvoice(Invoice invoice) {
        if (invoice == null) return null;

        Optional<Invoice> opt = repository.findById(invoice.getInvoiceID());
        if (opt.isPresent()) {
            Invoice existing = opt.get();

            // Update fields (only if incoming values are not null/empty - you can adjust rules)
            if (invoice.getInvoiceDate() != null) existing.setInvoiceDate(invoice.getInvoiceDate());
            if (invoice.getDueDate() != null) existing.setDueDate(invoice.getDueDate());
            if (invoice.getStatus() != null) existing.setStatus(invoice.getStatus());
            existing.setSubtotal(invoice.getSubtotal());
            existing.setTaxAmount(invoice.getTaxAmount());
            existing.setDiscountAmount(invoice.getDiscountAmount());
            existing.setTotalAmount(invoice.getTotalAmount());
            if (invoice.getPaymentMethod() != null) existing.setPaymentMethod(invoice.getPaymentMethod());
            if (invoice.getPaymentReference() != null) existing.setPaymentReference(invoice.getPaymentReference());

            return repository.save(existing);
        }
        return null; // not found
    }

    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    public Invoice getInvoiceByPaymentReference(String paymentReference) {
        return repository.findByPaymentReference(paymentReference).orElse(null);
    }

    public void deleteInvoice(int id) {
        repository.deleteById(id);
    }
}

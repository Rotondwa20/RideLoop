package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService service;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Invoice create(@RequestBody Invoice invoice) {
        return service.createInvoice(invoice);
    }

    @GetMapping("/read/{id}")
    public Invoice read(@PathVariable Integer id) {
        return service.readInvoice(id);
    }

    @PutMapping("/update")
    public Invoice update(@RequestBody Invoice invoice) {
        return service.updateInvoice(invoice);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteInvoice(id);
    }

    @GetMapping("/getAll")
    public List<Invoice> getAll() {
        return service.getAllInvoices();
    }

    @GetMapping("/get-by-reference/{paymentReference}")
    public Invoice getByPaymentReference(@PathVariable String paymentReference) {
        return service.getInvoiceByPaymentReference(paymentReference);
    }
}
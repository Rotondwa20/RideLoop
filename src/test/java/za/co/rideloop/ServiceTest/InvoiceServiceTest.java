package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Factory.InvoiceFactory;
import za.co.rideloop.Service.InvoiceService;
import za.co.rideloop.Repository.InvoiceRepository;

import java.time.LocalDate;
import java.util.List;
/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InvoiceServiceTest {

    @Autowired
    private InvoiceService service;

    @Autowired
    private InvoiceRepository repository;

    private Invoice sampleInvoice;

    @BeforeEach
    void setUp() {
        sampleInvoice = InvoiceFactory.createInvoice(
                LocalDate.of(2023, 10, 28),
                LocalDate.of(2023, 11, 28),
                "Pending",
                2500.00,
                200.00,
                0.00,
                2700.00,
                "EFT",
                "INV-2025-027"
        );
    }

    @Test
    @Commit
    void create() {
        Invoice saved = service.create(sampleInvoice);
        assertNotNull(saved);
        assertEquals("EFT", saved.getPaymentMethod());
        System.out.println("Created Invoice: " + saved);
    }

    @Test
    void read() {
        Invoice saved = service.create(sampleInvoice);
        Invoice found = service.read(saved.getInvoiceID());
        assertNotNull(found);
        assertEquals(saved.getInvoiceID(), found.getInvoiceID());
        System.out.println("Read Invoice: " + found);
    }

    @Test
    @Commit
    void update() {
        Invoice saved = service.create(sampleInvoice);
        Invoice updated = new Invoice.InvoiceBuilder()
                .InvoiceBuilderCopy(saved)
                .setStatus("Paid")
                .setPaymentMethod("Cash")
                .build();
        Invoice result = service.update(updated);
        assertNotNull(result);
        assertEquals("Paid", result.getStatus());
        assertEquals("Cash", result.getPaymentMethod());
        System.out.println("Updated Invoice: " + result);
    }

    @Test
    void getAll() {
        service.create(sampleInvoice);
        Invoice secondInvoice = InvoiceFactory.createInvoice(
                LocalDate.of(2023, 10, 29),
                LocalDate.of(2023, 11, 29),
                "Unpaid",
                1500.00,
                120.00,
                0.00,
                1620.00,
                "Card",
                "INV-2025-028"
        );
        service.create(secondInvoice);
        List<Invoice> all = service.getAll();
        System.out.println("All Invoices: \n" + all);
    }

//    @Test
//    void getInvoiceByPaymentReference() {
//        Invoice saved = service.create(sampleInvoice);
//        Invoice found = service.getInvoiceByPaymentReference(saved.getPaymentReference());
//        assertNotNull(found);
//        assertEquals(saved.getInvoiceID(), found.getInvoiceID());
//        System.out.println("Found by Payment Reference: " + found);
//    }

    @Test
    void delete() {
        Invoice saved = service.create(sampleInvoice);
        int idToDelete = saved.getInvoiceID();
        service.delete(idToDelete);
        assertNull(service.read(idToDelete));
        System.out.println("Deleted Invoice with ID: " + idToDelete);
    }
}

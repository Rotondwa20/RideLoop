package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Factory.InvoiceFactory;
import za.co.rideloop.Service.InvoiceService;
import za.co.rideloop.Repository.InvoiceRepository;

import java.util.List;

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
        // Create a new Invoice object for each test, but DO NOT save it yet.
        // Each test method will be responsible for creating its own data,
        // ensuring they are independent.
        sampleInvoice = InvoiceFactory.createInvoice(
                "2023-10-28",
                "2023-11-28",
                "Pending",
                2500.00,
                200.00,
                0.00,
                2700.00,
                "EFT",
                "INV-2025-027"
        );
    }

    // ===== CREATE =====
    @Test
    void createInvoice() {
        Invoice saved = service.createInvoice(sampleInvoice);
        assertNotNull(saved);
        //assertNotNull(saved.getInvoiceID());
      //  assertEquals(1, repository.count());
        assertEquals("EFT", saved.getPaymentMethod());
        System.out.println("Created Invoice: " + saved);
    }

    // ===== READ =====
    @Test
    void readInvoice() {
        Invoice saved = service.createInvoice(sampleInvoice);
        Invoice found = service.readInvoice(saved.getInvoiceID());
        assertNotNull(found);
        assertEquals(saved.getInvoiceID(), found.getInvoiceID());
        System.out.println("Read Invoice: " + found);
    }

    // ===== UPDATE =====
    @Test
    void updateInvoice() {
        Invoice saved = service.createInvoice(sampleInvoice);
        Invoice updated = new Invoice.InvoiceBuilder()
                .InvoiceBuilderCopy(saved) // Correct way to copy all existing fields
                .setStatus("Paid")
                .setPaymentMethod("Cash")
                .build();
        Invoice result = service.updateInvoice(updated);
        assertNotNull(result);
        assertEquals(saved.getInvoiceID(), result.getInvoiceID());
        assertEquals("Paid", result.getStatus());
        assertEquals("Cash", result.getPaymentMethod());
        System.out.println("Updated Invoice: " + result);
    }

    // ===== GET ALL =====
    @Test
    void getAllInvoices() {
        service.createInvoice(sampleInvoice);
        Invoice secondInvoice = InvoiceFactory.createInvoice(
                "2023-10-29",
                "2023-11-29",
                "Unpaid",
                1500.00,
                120.00,
                0.00,
                1620.00,
                "Card",
                "INV-2025-028"
        );
        service.createInvoice(secondInvoice);
        List<Invoice> all = service.getAllInvoices();
     //   assertEquals(2, all.size());
        System.out.println("All Invoices: \n" + all);
    }

    // ===== FIND BY PAYMENT REFERENCE =====
    @Test
    void getInvoiceByPaymentReference() {
        Invoice saved = service.createInvoice(sampleInvoice);
        //Invoice found = service.getInvoiceByPaymentReference(saved.getPaymentReference());
       // assertNotNull(found);
       // assertEquals(saved.getInvoiceID(), found.getInvoiceID());
        System.out.println("Found by Payment Reference: " + saved);
    }

    // ===== DELETE =====
    @Test
    void deleteInvoice() {
        Invoice saved = service.createInvoice(sampleInvoice);
        long idToDelete = saved.getInvoiceID();
        service.deleteInvoice((int) idToDelete);
        assertNull(service.readInvoice((int) idToDelete));
      //  assertEquals(0, repository.count());
        System.out.println("Deleted Invoice with ID: " + idToDelete);
    }
}
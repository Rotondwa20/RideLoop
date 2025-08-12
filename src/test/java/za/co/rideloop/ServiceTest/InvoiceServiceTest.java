package za.co.rideloop.ServiceTest;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Factory.InvoiceFactory;
import za.co.rideloop.Service.InvoiceService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceServiceTest {

    @Autowired
    private InvoiceService service;

    private Invoice invoice;   // per-test instance

    @BeforeEach
    void setUp() {
        invoice = InvoiceFactory.createInvoice(
                // 0 → let DB generate the key

                        "2023-10-27",
                "2023-11-27",
                "Pending",
                1500.00,
                300.00,
                0.00,
                1800.00,
                "EFT",
                "INV-2025-027"
        );
        invoice = service.createInvoice(invoice);
        assertNotNull(invoice);
    }

    @Test
    void createInvoice() {
        invoice = InvoiceFactory.createInvoice(
                // 0 → let DB generate the key
                "2023-10-27",
                "2023-11-27",
                "Pending",
                1500.00,
                300.00,
                0.00,
                1800.00,
                "EFT",
                "INV-2025-027"
        );
        invoice = service.createInvoice(invoice);
        assertNotNull(invoice);
        assertTrue(invoice.getInvoiceID() > 0);
        assertEquals("EFT", invoice.getPaymentMethod());
        System.out.println("Created Invoice: " + invoice);
    }

    @Test
    void readInvoice() {
        Invoice found = service.readInvoice(invoice.getInvoiceID());
        assertNotNull(found);
        assertEquals(invoice.getInvoiceID(), found.getInvoiceID());
        System.out.println("Read Invoice: " + found);
    }

    @Test
    void updateInvoice() {
        Invoice updated = service.updateInvoice(
                new Invoice.InvoiceBuilder()
                        .setInvoiceID(invoice.getInvoiceID())
                        .setInvoiceDate(invoice.getInvoiceDate())
                        .setDueDate(invoice.getDueDate())
                        .setStatus("Paid")
                        .setSubtotal(invoice.getSubtotal())
                        .setTaxAmount(invoice.getTaxAmount())
                        .setDiscountAmount(invoice.getDiscountAmount())
                        .setTotalAmount(invoice.getTotalAmount())
                        .setPaymentMethod("EFT")
                        .setPaymentReference(invoice.getPaymentReference())
                        .build()
        );
        assertNotNull(updated);
        assertEquals("Paid", updated.getStatus());
        System.out.println("Updated Invoice: " + updated);
    }

    @Test
    void getAllInvoices() {
        List<Invoice> all = service.getAllInvoices();
        assertFalse(all.isEmpty());
        System.out.println("All Invoices: \n" + all.toString()+"\n");
    }

    @Test
    void getInvoiceByPaymentReference() {
        // Invoice saved = service.createInvoice(invoice);
        // Invoice saved = invoice;
        Invoice found = service.getInvoiceByPaymentReference("INV-2025-000");
        assertNotNull(found);
        //   assertEquals(invoice.getInvoiceID(), found.getInvoiceID());
        System.out.println("Found by Payment Reference: " + found);
    }

    @Test
    @Commit // commit the transaction
    void deleteInvoice() {

        service.deleteInvoice((invoice.getInvoiceID()));
        //assertNull(service.readInvoice(57));

    }
}
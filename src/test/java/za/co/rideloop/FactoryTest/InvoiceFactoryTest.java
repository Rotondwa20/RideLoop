package za.co.rideloop.FactoryTest;
/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Factory.InvoiceFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InvoiceFactoryTest {

    @Test
    void createInvoice() {
        int invoiceID = 101;
        String invoiceDate = "2023-10-01";
        String dueDate = "2023-10-15";
        String status = "Unpaid";
        double subtotal = 1000.00;
        double taxAmount = 150.00;
        double discountAmount = 50.00;
        double totalAmount = 1100.00;
        String paymentMethod = "Credit Card";
        String paymentReference = "INV-REF-101";

        Invoice invoice = InvoiceFactory.createInvoice(
                invoiceID,
                invoiceDate,
                dueDate,
                status,
                subtotal,
                taxAmount,
                discountAmount,
                totalAmount,
                paymentMethod,
                paymentReference);

        assertNotNull(invoice);
        assertEquals(invoiceID, invoice.getInvoiceID());
        assertEquals(invoiceDate, invoice.getInvoiceDate());
        assertEquals(dueDate, invoice.getDueDate());
        assertEquals(status, invoice.getStatus());
        //Uses assertEquals with a delta (0.001) for double comparisons to handle floating-point precision
        assertEquals(subtotal, invoice.getSubtotal(), 0.001); // Using delta for double comparison
        assertEquals(taxAmount, invoice.getTaxAmount(), 0.001);
        assertEquals(discountAmount, invoice.getDiscountAmount(), 0.001);
        assertEquals(totalAmount, invoice.getTotalAmount(), 0.001);
        assertEquals(paymentMethod, invoice.getPaymentMethod());
        assertEquals(paymentReference, invoice.getPaymentReference());
        System.out.println(invoice);
    }
}
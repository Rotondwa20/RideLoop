package za.co.rideloop.Factory;
/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 **/
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.util.Helper;

public class InvoiceFactory {
    public static Invoice createInvoice(int invoiceID, String invoiceDate, String dueDate, String status,
                                        double subtotal, double taxAmount, double discountAmount,
                                        double totalAmount, String paymentMethod, String paymentReference) {

        // Use the Helper class to validate the input parameters
        if (!Helper.isValidInterger(invoiceID) ||
                Helper.isNullOrEmpty(invoiceDate) ||
                Helper.isNullOrEmpty(dueDate) ||
                Helper.isNullOrEmpty(status) ||
                !Helper.isValidAmount(subtotal) ||
                !Helper.isValidAmount(totalAmount)) {
            // Handle invalid input, for example, by throwing an IllegalArgumentException
            throw new IllegalArgumentException("Invalid input provided for creating an Invoice.");
        }

        return new Invoice.InvoiceBuilder()
                .setInvoiceID(invoiceID)
                .setInvoiceDate(invoiceDate)
                .setDueDate(dueDate)
                .setStatus(status)
                .setSubtotal(subtotal)
                .setTaxAmount(taxAmount)
                .setDiscountAmount(discountAmount)
                .setTotalAmount(totalAmount)
                .setPaymentMethod(paymentMethod)
                .setPaymentReference(paymentReference)
                .build();
    }
}
package za.co.rideloop.Factory;
/**
 * InvoiceFactory.java
 * InvoiceFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 **/
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Util.Helper;
import java.time.LocalDate;

public class InvoiceFactory {
    public static Invoice createInvoice(LocalDate invoiceDate, LocalDate dueDate, String status,
                                        double subtotal, double taxAmount, double discountAmount,
                                        double totalAmount, String paymentMethod, String paymentReference) {

        if (invoiceDate == null || dueDate == null ||
                Helper.isNullOrEmpty(status) ||
                !Helper.isValidAmount(subtotal) ||
                !Helper.isValidAmount(totalAmount)) {
            throw new IllegalArgumentException("Invalid input provided for creating an Invoice.");
        }

        return new Invoice.InvoiceBuilder()
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

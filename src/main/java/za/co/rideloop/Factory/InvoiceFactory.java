package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Invoice;

public class InvoiceFactory {
    public static Invoice createInvoice(int invoiceID, String invoiceDate, String dueDate, String status,
                                        double subtotal, double taxAmount, double discountAmount,
                                        double totalAmount, String paymentMethod, String paymentReference) {
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

package za.co.rideloop.Domain;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Invoice.java
 * Invoice Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * group 3I
 **/
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceID;

    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private String status;
    private double subtotal;
    private double taxAmount;
    private double discountAmount;
    private double totalAmount;
    private String paymentMethod;
    private String paymentReference;

    protected Invoice() {}

    private Invoice(InvoiceBuilder builder) {

        this.invoiceID = builder.invoiceID;
        this.invoiceDate = builder.invoiceDate;
        this.dueDate = builder.dueDate;
        this.status = builder.status;
        this.subtotal = builder.subtotal;
        this.taxAmount = builder.taxAmount;
        this.discountAmount = builder.discountAmount;
        this.totalAmount = builder.totalAmount;
        this.paymentMethod = builder.paymentMethod;
        this.paymentReference = builder.paymentReference;
    }

    // ----- Getters -----
    public int getInvoiceID() { return invoiceID; }
    public LocalDate getInvoiceDate() { return invoiceDate; }
    public LocalDate getDueDate() { return dueDate; }
    public String getStatus() { return status; }
    public double getSubtotal() { return subtotal; }
    public double getTaxAmount() { return taxAmount; }
    public double getDiscountAmount() { return discountAmount; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentReference() { return paymentReference; }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID=" + invoiceID +
                ", invoiceDate=" + invoiceDate +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                ", subtotal=" + subtotal +
                ", taxAmount=" + taxAmount +
                ", discountAmount=" + discountAmount +
                ", totalAmount=" + totalAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentReference='" + paymentReference + '\'' +
                '}';
    }

    // ----- Builder -----
    public static class InvoiceBuilder {
        private int invoiceID;
        private LocalDate invoiceDate;
        private LocalDate dueDate;
        private String status;
        private double subtotal;
        private double taxAmount;
        private double discountAmount;
        private double totalAmount;
        private String paymentMethod;
        private String paymentReference;

        public InvoiceBuilder setInvoiceID(int invoiceID) {
            this.invoiceID = invoiceID;
            return this;
        }
        public InvoiceBuilder setInvoiceDate(LocalDate invoiceDate) {
            this.invoiceDate = invoiceDate;
            return this;
        }
        public InvoiceBuilder setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }
        public InvoiceBuilder setStatus(String status) {
            this.status = status;
            return this;
        }
        public InvoiceBuilder setSubtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }
        public InvoiceBuilder setTaxAmount(double taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }
        public InvoiceBuilder setDiscountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }
        public InvoiceBuilder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }
        public InvoiceBuilder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }
        public InvoiceBuilder setPaymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        public InvoiceBuilder InvoiceBuilderCopy(Invoice invoice) {
            this.invoiceID = invoice.invoiceID;
            this.invoiceDate = invoice.invoiceDate;
            this.dueDate = invoice.dueDate;
            this.status = invoice.status;
            this.subtotal = invoice.subtotal;
            this.taxAmount = invoice.taxAmount;
            this.discountAmount = invoice.discountAmount;
            this.totalAmount = invoice.totalAmount;
            this.paymentMethod = invoice.paymentMethod;
            this.paymentReference = invoice.paymentReference;
            return this;
        }

        public Invoice build() { return new Invoice(this); }
    }
}

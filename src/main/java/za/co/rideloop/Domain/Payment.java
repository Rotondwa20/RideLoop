package za.co.rideloop.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Payment.java
 * Payment Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 **/
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private int rentalID;
    private Double paymentAmount;
    private String paymentMethod;
    private LocalDate paymentDate;
    private String paymentStatus;

    protected Payment() {
    }

    // Private constructor to ensure that instances are created through the builder
    private Payment(PaymentBuilder builder) {
        this.paymentId = builder.paymentId;
        this.rentalID = builder.rentalID;
        this.paymentAmount = builder.paymentAmount;
        this.paymentMethod = builder.paymentMethod;
        this.paymentDate = builder.paymentDate;
        this.paymentStatus = builder.paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getRentalID() {
        return rentalID;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", rentalID=" + rentalID +
                ", paymentAmount=" + paymentAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public static class PaymentBuilder {
        private int paymentId;
        private int rentalID;
        private Double paymentAmount;
        private String paymentMethod;
        private LocalDate paymentDate;
        private String paymentStatus;


        public PaymentBuilder setPaymentId(int paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        // Fixed typo in the setter name
        public PaymentBuilder setRentalID(int rentalID) {
            this.rentalID = rentalID;
            return this;
        }

        public PaymentBuilder setPaymentAmount(Double paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public PaymentBuilder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public PaymentBuilder setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public PaymentBuilder setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }
        public PaymentBuilder PaymentBuilderCopy(Payment payment) {
            this.paymentId = payment.paymentId;
            this.rentalID = payment.rentalID;
            this.paymentAmount = payment.paymentAmount;
            this.paymentMethod = payment.paymentMethod;
            this.paymentDate = payment.paymentDate;
            this.paymentStatus = payment.paymentStatus;
            return this;
        }

        public Payment build() {

            return new Payment(this);
        }
    }
}

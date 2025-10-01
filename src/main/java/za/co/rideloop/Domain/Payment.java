package za.co.rideloop.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Payment.java
 * Payment Model Class
 *
 * @Author:n Ndyebo Qole
 * @Student Number: 210018615
 **/
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;



    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "rentalID", nullable = false)
    private Rental rental;

    private Double paymentAmount;
    private String paymentMethod;
    private LocalDate paymentDate;
    private String paymentStatus;

    protected Payment() {}

    private Payment(PaymentBuilder builder) {
        this.paymentId = builder.paymentId;
        this.rental = builder.rental;
        this.paymentAmount = builder.paymentAmount;
        this.paymentMethod = builder.paymentMethod;
        this.paymentDate = builder.paymentDate;
        this.paymentStatus = builder.paymentStatus;
    }

    // Getters
    public Long getPaymentId() { return paymentId; }
    public Rental getRental() { return rental; }
    public Double getPaymentAmount() { return paymentAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public String getPaymentStatus() { return paymentStatus; }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", rental=" + (rental != null ? rental.getRentalID() : "null") +
                ", paymentAmount=" + paymentAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public static class PaymentBuilder {
        private Long paymentId;
        private Rental rental;
        private Double paymentAmount;
        private String paymentMethod;
        private LocalDate paymentDate;
        private String paymentStatus;

        public PaymentBuilder setPaymentId(Long paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        // 🔥 This is the missing method
        public PaymentBuilder setRental(Rental rental) {
            this.rental = rental;
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
            this.rental = payment.rental;
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

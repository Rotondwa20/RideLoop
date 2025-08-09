package za.co.rideloop.Domain;

/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 **/

public class Payment {

    private int paymentId;
    private int rentalID;
    private Double paymentAmount;
    private String paymentMethod;
    private String paymentDate;
    private String paymentStatus;

    public Payment() {
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

    public String getPaymentDate() {
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
        private String paymentDate;
        private String paymentStatus;

        public PaymentBuilder() {
        }

        // Parameterized constructor to set all fields at once
        public PaymentBuilder(int paymentId, int rentalID, Double paymentAmount, String paymentMethod, String paymentDate, String paymentStatus) {
            this.paymentId = paymentId;
            this.rentalID = rentalID;
            this.paymentAmount = paymentAmount;
            this.paymentMethod = paymentMethod;
            this.paymentDate = paymentDate;
            this.paymentStatus = paymentStatus;
        }

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

        public PaymentBuilder setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public PaymentBuilder setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Payment build() {

            return new Payment(this);
        }
    }
}

package za.co.rideloop.Domain;
/* Car.java

     CAR POJO class

     Author: Natasha Njili(221785345)

     Date: 11 May 2025 */
public class Car {

    private int carId;
    private String brand;
    private String model;
    private int year;
    private String licensePlate;
    private double rentalRate;
    private String status;
    private String category;
    private int mileage;
    private String lastMaintenance;
    private String maintenanceDue;

    private Car(Builder builder) {
        this.carId = builder.carId;
        this.brand = builder.brand;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.rentalRate = builder.rentalRate;
        this.status = builder.status;
        this.category = builder.category;
        this.mileage = builder.mileage;
        this.lastMaintenance = builder.lastMaintenance;
        this.maintenanceDue = builder.maintenanceDue;
    }

    public static class Builder {
        private int carId;
        private String brand;
        private String model;
        private int year;
        private String licensePlate;
        private double rentalRate;
        private String status;
        private String category;
        private int mileage;
        private String lastMaintenance;
        private String maintenanceDue;

        public Builder setCarId(int carId) {
            this.carId = carId;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setRentalRate(double rentalRate) {
            this.rentalRate = rentalRate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Builder setLastMaintenance(String lastMaintenance) {
            this.lastMaintenance = lastMaintenance;
            return this;
        }

        public Builder setMaintenanceDue(String maintenanceDue) {
            this.maintenanceDue = maintenanceDue;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", licensePlate='" + licensePlate + '\'' +
                ", rentalRate=" + rentalRate +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                ", mileage=" + mileage +
                ", lastMaintenance='" + lastMaintenance + '\'' +
                ", maintenanceDue='" + maintenanceDue + '\'' +
                '}';
    }

}

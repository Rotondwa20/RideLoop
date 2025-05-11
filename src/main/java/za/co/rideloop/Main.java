package za.co.rideloop;

import za.co.rideloop.Domain.Car;
import za.co.rideloop.Domain.Maintenance;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello RideLoop!");

        //creating a car object
        Car car = new Car.Builder()
                .setCarId(101)
                .setBrand("Toyota")
                .setModel("Corolla")
                .setYear(2020)
                .setLicensePlate("CA123456")
                .setRentalRate(650.00)
                .setStatus("Available")
                .setCategory("Sedan")
                .setMileage(45000)
                .setLastMaintenance("2024-12-10")
                .setMaintenanceDue("2025-06-10")
                .build();

        System.out.println(car);

    //creating a maintenance object
    Maintenance maintenance = new Maintenance.Builder()
            .setInsuranceCompanyName("ABC Insurance Ltd.")
            .setContactPerson("John Doe")
            .setContactNumber("0781234567")
            .setCoverageType("Full Coverage")
            .setCostPerMonth(899.99)
            .setDescription("Covers accidents, theft, and natural disasters")
            .build();

        System.out.println(maintenance);
}}


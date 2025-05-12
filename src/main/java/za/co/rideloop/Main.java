package za.co.rideloop;

import za.co.rideloop.Domain.*;

import java.util.Date;

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


        // Create Address
        Address address = new Address.Builder()
                .setStreetName("123 Hope Street")
                .setSuburb("Ennerdale Ext 4")
                .setProvince("Gauteng")
                .setZipCode("1830")
                .build();

        // Create ContactDetails
        ContactDetails contactDetails = new ContactDetails.Builder()
                .setContactEmail("rotondwa@example.com")
                .setContactNumber("0723456789")
                .setAlternativeContactNum("0781234567")
                .build();

        // Create Customer
        Customer customer = new Customer.Builder()
                .setCustomerID(1)
                .setFirstName("Rotondwa")
                .setLastName("Rambau")
                .setLicenseNumber("CPT123456")
                .setUsername("rotondwa22")
                .setPassword("myPassword123")
                .setStatus("Active")
                .setAddress(address)
                .setContactDetails(contactDetails)
                .build();

        // Create carSupplier object
        CarSupplier supplier = new CarSupplier.Builder()
                .supplierID(1001)
                .name("AutoParts Inc.")
                .contactPerson("John Smith")
                .supplyDate(new Date())
                .contractStatus("Active")
                .build();



        System.out.println("Customer ID: " + customer.getCustomerID());
        System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("License No: " + customer.getLicenseNumber());
        System.out.println("Username: " + customer.getUsername());
        System.out.println("Status: " + customer.getStatus());

        System.out.println("Address: " + customer.getAddress().getStreetName() + ", " +
                customer.getAddress().getSuburb() + ", " +
                customer.getAddress().getProvince() + ", " +
                customer.getAddress().getZipCode());

        System.out.println("Contact Email: " + customer.getContactDetails().getContactEmail());
        System.out.println("Phone: " + customer.getContactDetails().getContactNumber());
        System.out.println("Alt Phone: " + customer.getContactDetails().getAlternativeContactNum());






    }}


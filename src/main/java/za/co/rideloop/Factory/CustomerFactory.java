package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.ContactDetails;
import za.co.rideloop.Domain.Customer;

/**
 * CustomerFactory.java

 * Author:Rotondwa Rambau
 * Student Number: 222342145
 * Group:3I
 */
public class CustomerFactory {

    public static Customer createCustomer(
            int customerID,
            String firstName,
            String lastName,
            String email,
            String phone,
            String licenseNumber,
            String username,
            String password,
            String status,
            String streetName,
            String suburb,
            String province,
            String zipCode,
            String contactEmail,
            String contactNumber,
            String alternativeContactNum) {

        // Create Address
        Address address = new Address.Builder()
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setProvince(province)
                .setZipCode(zipCode)
                .build();

        // Create ContactDetails
        ContactDetails contactDetails = new ContactDetails.Builder()
                .setContactEmail(contactEmail)
                .setContactNumber(contactNumber)
                .setAlternativeContactNum(alternativeContactNum)
                .build();

        // Create and return Customer
        return new Customer.Builder()
                .setCustomerID(customerID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhone(phone)
                .setLicenseNumber(licenseNumber)
                .setUsername(username)
                .setPassword(password)
                .setStatus(status)
                .setAddress(address)
                .setContactDetails(contactDetails)
                .build();
    }

}
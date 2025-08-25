package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.ContactDetails;
import za.co.rideloop.Domain.Customer;

import java.util.UUID;

public class CustomerFactory {

    public static Customer createCustomer(
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

        Address address = new Address.Builder()
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setProvince(province)
                .setZipCode(zipCode)
                .build();

        ContactDetails contactDetails = new ContactDetails.Builder()
                .setContactEmail(contactEmail)
                .setContactNumber(contactNumber)
                .setAlternativeContactNum(alternativeContactNum)
                .build();

        return new Customer.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhone(phone)
                .setLicenseNumber(licenseNumber)
                .setUsername(username)
                .setPassword(password != null ? password : UUID.randomUUID().toString())
                .setStatus(status)
                .setAddress(address)
                .setContactDetails(contactDetails)
                .build();
    }
}
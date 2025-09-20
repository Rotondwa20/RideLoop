package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;

public class CustomerProfileFactory {

    public static CustomerProfile createCustomerProfile(User user, String firstName, String lastName,
                                                        String idNumber, String phoneNumber, String status) {
        return new CustomerProfile.Builder()
                .setUser(user)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setIdNumber(idNumber)
                .setPhoneNumber(phoneNumber)
                .setStatus(status)
                .build();
    }

    // Creates a full CustomerProfile with all optional fields (without documents)
    public static CustomerProfile createFullCustomerProfile(User user, String firstName, String lastName,
                                                            String idNumber, String licenseNumber, String phoneNumber,
                                                            Address address, String status) {
        return new CustomerProfile.Builder()
                .setUser(user)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setIdNumber(idNumber)
                .setLicenseNumber(licenseNumber)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .setStatus(status)
                .build();
    }
}

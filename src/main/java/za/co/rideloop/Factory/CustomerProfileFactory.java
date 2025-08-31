package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;

public class CustomerProfileFactory {

    // Creates a basic CustomerProfile with mandatory fields
    public static CustomerProfile createCustomerProfile(User user, String firstName, String lastName,
                                                        String idNumber, String phoneNumber) {
        return new CustomerProfile.Builder()
                .setUser(user)               // link to user
                .setFirstName(firstName)
                .setLastName(lastName)
                .setIdNumber(idNumber)
                .setPhoneNumber(phoneNumber)
                .build();
    }

    // Creates a full CustomerProfile with all optional fields
    public static CustomerProfile createFullCustomerProfile(User user, String firstName, String lastName,
                                                            String idNumber, String licenseNumber, String phoneNumber,
                                                            Address address, byte[] idDocument, byte[] licenseDocument,
                                                            byte[] proofOfAddress, byte[] profileImage) {
        return new CustomerProfile.Builder()
                .setUser(user)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setIdNumber(idNumber)
                .setLicenseNumber(licenseNumber)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .setIdDocument(idDocument)
                .setLicenseDocument(licenseDocument)
                .setProofOfAddress(proofOfAddress)
                .setProfileImage(profileImage)
                .build();
    }
}

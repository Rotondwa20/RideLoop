package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Util.CustomerProfileValidator;

public class CustomerProfileFactory {

    public static CustomerProfile createCustomerProfile(
            String firstName,
            String lastName,
            String idNumber,
            String licenseNumber,
            String phoneNumber,
            User user,
            Address address,
            byte[] idDocument,
            byte[] licenseDoc,
            byte[] idCopy,
            byte[] proofOfResidence,
            byte[] profilePicture // new parameter
    ) throws IllegalArgumentException {

        // ---------- Validate input ----------
        CustomerProfileValidator.validateText(firstName, "First Name");
        CustomerProfileValidator.validateText(lastName, "Last Name");
        CustomerProfileValidator.validateNumber(idNumber, "ID Number");
        CustomerProfileValidator.validateText(licenseNumber, "License Number"); // can be alphanumeric
        CustomerProfileValidator.validateNumber(phoneNumber, "Phone Number");

        if (user == null) throw new IllegalArgumentException("User cannot be null.");
        if (address == null) throw new IllegalArgumentException("Address cannot be null.");
        if (idDocument == null || idDocument.length == 0) throw new IllegalArgumentException("ID Document must be uploaded.");
        if (licenseDoc == null || licenseDoc.length == 0) throw new IllegalArgumentException("License Document must be uploaded.");
        if (idCopy == null || idCopy.length == 0) throw new IllegalArgumentException("ID Copy must be uploaded.");
        if (proofOfResidence == null || proofOfResidence.length == 0) throw new IllegalArgumentException("Proof of Residence must be uploaded.");
        if (profilePicture == null || profilePicture.length == 0) throw new IllegalArgumentException("Profile Picture must be uploaded.");

        // ---------- Build CustomerProfile ----------
        return new CustomerProfile.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setIdNumber(idNumber)
                .setLicenseNumber(licenseNumber)
                .setPhoneNumber(phoneNumber)
                .setUser(user)
                .setAddress(address)
                .setIdDocument(idDocument)
                .setLicenseDoc(licenseDoc)
                .setIdCopy(idCopy)
                .setProofOfResidence(proofOfResidence)
                .setProfilePicture(profilePicture) // set profile picture
                .build();
    }
}

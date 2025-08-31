package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Address;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Service.CustomerProfileService;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService service;

    // ---------------- DTO class for receiving profile data ----------------
    public static class CustomerProfileDTO {
        public String firstName;
        public String lastName;
        public String idNumber;
        public String licenseNumber;
        public String phoneNumber;
        public Address address;
        public String idDocumentBase64;
        public String licenseDocumentBase64;
        public String proofOfAddressBase64;
        public String profileImageBase64;
    }

    // ---------------- Get or create profile for the logged-in user ----------------
    @GetMapping("/me")
    public CustomerProfile getOrCreateProfile(@RequestParam int userID) {
        CustomerProfile profile = service.getProfileByUserId(userID);

        if (profile == null) {
            profile = new CustomerProfile();
            User user = service.getUserById(userID); // make sure this method exists
            profile.setUser(user);
            profile.setFirstName("");
            profile.setLastName("");
            profile.setAddress(new Address());
            profile = service.createProfile(profile); // save to DB
        }

        return profile;
    }

    // ---------------- Create or update profile for the logged-in user ----------------
    @PostMapping("/me")
    public CustomerProfile createOrUpdateMyProfile(
            @RequestBody CustomerProfileDTO dto,
            @RequestParam int userID) {

        CustomerProfile profile = new CustomerProfile();
        profile.setFirstName(dto.firstName);
        profile.setLastName(dto.lastName);
        profile.setIdNumber(dto.idNumber);
        profile.setLicenseNumber(dto.licenseNumber);
        profile.setPhoneNumber(dto.phoneNumber);
        profile.setAddress(dto.address);

        profile.setIdDocument(dto.idDocumentBase64 != null ? Base64.getDecoder().decode(dto.idDocumentBase64) : null);
        profile.setLicenseDocument(dto.licenseDocumentBase64 != null ? Base64.getDecoder().decode(dto.licenseDocumentBase64) : null);
        profile.setProofOfAddress(dto.proofOfAddressBase64 != null ? Base64.getDecoder().decode(dto.proofOfAddressBase64) : null);
        profile.setProfileImage(dto.profileImageBase64 != null ? Base64.getDecoder().decode(dto.profileImageBase64) : null);

        return service.saveProfileForUser(userID, profile);
    }

    // ---------------- Create a profile (for admin or other users) ----------------
    @PostMapping
    public CustomerProfile createProfile(@RequestBody CustomerProfileDTO dto) {
        CustomerProfile profile = new CustomerProfile();
        profile.setFirstName(dto.firstName);
        profile.setLastName(dto.lastName);
        profile.setIdNumber(dto.idNumber);
        profile.setLicenseNumber(dto.licenseNumber);
        profile.setPhoneNumber(dto.phoneNumber);
        profile.setAddress(dto.address);
        profile.setIdDocument(dto.idDocumentBase64 != null ? Base64.getDecoder().decode(dto.idDocumentBase64) : null);
        profile.setLicenseDocument(dto.licenseDocumentBase64 != null ? Base64.getDecoder().decode(dto.licenseDocumentBase64) : null);
        profile.setProofOfAddress(dto.proofOfAddressBase64 != null ? Base64.getDecoder().decode(dto.proofOfAddressBase64) : null);
        profile.setProfileImage(dto.profileImageBase64 != null ? Base64.getDecoder().decode(dto.profileImageBase64) : null);

        return service.createProfile(profile);
    }

    // ---------------- Get a profile by profile ID ----------------
    @GetMapping("/{id}")
    public CustomerProfile getProfile(@PathVariable int id) {
        CustomerProfile profile = service.readProfile(id);
        if (profile == null) {
            throw new RuntimeException("Profile not found with ID: " + id);
        }
        return profile;
    }
    @PutMapping("/{id}")
    public CustomerProfile updateProfile(@PathVariable int id, @RequestBody CustomerProfileDTO dto) {
        // Fetch existing profile
        CustomerProfile profile = service.readProfile(id);
        if (profile == null) {
            throw new RuntimeException("Profile not found with ID: " + id);
        }

        // Update fields
        profile.setFirstName(dto.firstName);
        profile.setLastName(dto.lastName);
        profile.setIdNumber(dto.idNumber);
        profile.setLicenseNumber(dto.licenseNumber);
        profile.setPhoneNumber(dto.phoneNumber);

        // Ensure address is not null
        profile.setAddress(dto.address != null ? dto.address : new Address());

        // Safely decode Base64 fields
        profile.setIdDocument(safeDecode(dto.idDocumentBase64));
        profile.setLicenseDocument(safeDecode(dto.licenseDocumentBase64));
        profile.setProofOfAddress(safeDecode(dto.proofOfAddressBase64));
        profile.setProfileImage(safeDecode(dto.profileImageBase64));

        return service.updateProfile(profile);
    }

    // Utility method
    private byte[] safeDecode(String data) {
        return (data != null && !data.isEmpty()) ? Base64.getDecoder().decode(data) : null;
    }


    // ---------------- Delete a profile by profile ID ----------------
    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable int id) {
        service.deleteProfile(id);
    }

    // ---------------- Get all profiles ----------------
    @GetMapping
    public List<CustomerProfile> getAllProfiles() {
        return service.getAllProfiles();
    }
}

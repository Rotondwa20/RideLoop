package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Repository.CustomerProfileRepository;
import za.co.rideloop.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerProfileService {

    private final CustomerProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomerProfileService(CustomerProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public User getUserById(int userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userID));
    }

    public CustomerProfile createProfileForUser(CustomerProfile profile, int userID) {
        User user = getUserById(userID);
        profile.setUser(user);

        if (profile.getStatus() == null) {
            profile.setStatus("pending");
        }

        profileRepository.findByUser_Id(userID)
                .ifPresent(existing -> profile.setProfileID(existing.getProfileID()));

        return profileRepository.save(profile);
    }

    public CustomerProfile updateProfile(CustomerProfile profile, boolean isAdmin) {
        CustomerProfile existing = profileRepository.findById(profile.getProfileID())
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profile.getProfileID()));

        if (isAdmin) {
            existing.setStatus(profile.getStatus());
        }

        existing.setFirstName(profile.getFirstName());
        existing.setLastName(profile.getLastName());
        existing.setIdNumber(profile.getIdNumber());
        existing.setLicenseNumber(profile.getLicenseNumber());
        existing.setPhoneNumber(profile.getPhoneNumber());
        existing.setAddress(profile.getAddress());

        // Update documents if provided
        if (profile.getIdDocument() != null) existing.setIdDocument(profile.getIdDocument());
        if (profile.getLicenseDoc() != null) existing.setLicenseDoc(profile.getLicenseDoc());
        if (profile.getIdCopy() != null) existing.setIdCopy(profile.getIdCopy());
        if (profile.getProofOfResidence() != null) existing.setProofOfResidence(profile.getProofOfResidence());
        if (profile.getProfilePicture() != null) existing.setProfilePicture(profile.getProfilePicture());

        return profileRepository.save(existing);
    }

    public CustomerProfile readProfile(int profileID) {
        return profileRepository.findById(profileID)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileID));
    }

    public Optional<CustomerProfile> getProfileById(int profileID) {
        return profileRepository.findById(profileID);
    }

    public Optional<CustomerProfile> getProfileByUserId(int userID) {
        return profileRepository.findByUser_Id(userID);
    }

    public List<CustomerProfile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public List<CustomerProfile> getProfilesByStatus(String status) {
        return profileRepository.findByStatus(status);
    }

    public void deleteProfile(int profileID) {
        profileRepository.deleteById(profileID);
    }

    // ---------- Document Methods ----------
    public void addIdDocument(int profileID, byte[] document) {
        CustomerProfile profile = readProfile(profileID);
        profile.setIdDocument(document);
        profileRepository.save(profile);
    }

    public void addLicenseDoc(int profileID, byte[] document) {
        CustomerProfile profile = readProfile(profileID);
        profile.setLicenseDoc(document);
        profileRepository.save(profile);
    }

    public void addIdCopy(int profileID, byte[] document) {
        CustomerProfile profile = readProfile(profileID);
        profile.setIdCopy(document);
        profileRepository.save(profile);
    }

    public void addProofOfResidence(int profileID, byte[] document) {
        CustomerProfile profile = readProfile(profileID);
        profile.setProofOfResidence(document);
        profileRepository.save(profile);
    }

    public void addProfilePicture(int profileID, byte[] picture) {
        CustomerProfile profile = readProfile(profileID);
        profile.setProfilePicture(picture);
        profileRepository.save(profile);
    }

    // ---------- Document Getters ----------
    public byte[] getIdDocument(int profileID) {
        return readProfile(profileID).getIdDocument();
    }

    public byte[] getLicenseDoc(int profileID) {
        return readProfile(profileID).getLicenseDoc();
    }

    public byte[] getIdCopy(int profileID) {
        return readProfile(profileID).getIdCopy();
    }

    public byte[] getProofOfResidence(int profileID) {
        return readProfile(profileID).getProofOfResidence();
    }

    public byte[] getProfilePicture(int profileID) {
        return readProfile(profileID).getProfilePicture();
    }
}

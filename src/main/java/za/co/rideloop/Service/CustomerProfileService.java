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

        return profileRepository.save(existing);
    }


    // Direct return (throws exception if not found)
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
        return profileRepository.findByStatusIgnoreCase(status);
    }

    public void deleteProfile(int profileID) {
        profileRepository.deleteById(profileID);
    }
}

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

    @Autowired
    private CustomerProfileRepository repository;

    @Autowired
    private UserRepository userRepository;

    // ---------------- Get User by ID ----------------
    public User getUserById(int userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userID));
    }

    // ---------------- Create or update profile for a user ----------------
    public CustomerProfile createProfileForUser(CustomerProfile profile, int userID) {
        User user = getUserById(userID);
        profile.setUser(user);

        // Default status to "pending" if not set
        if (profile.getStatus() == null) {
            profile.setStatus("pending");
        }

        // If profile exists, preserve its ID
        repository.findByUser_Id(userID)
                .ifPresent(existing -> profile.setProfileID(existing.getProfileID()));

        return repository.save(profile);
    }

    // ---------------- Update profile ----------------
    // isAdmin = true allows status change, false keeps original status
    public CustomerProfile updateProfile(CustomerProfile profile, boolean isAdmin) {
        CustomerProfile existing = repository.findById(profile.getProfileID())
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profile.getProfileID()));

        // Only admin can change status
        if (!isAdmin) {
            profile.setStatus(existing.getStatus());
        }

        existing.setFirstName(profile.getFirstName());
        existing.setLastName(profile.getLastName());
        existing.setIdNumber(profile.getIdNumber());
        existing.setLicenseNumber(profile.getLicenseNumber());
        existing.setPhoneNumber(profile.getPhoneNumber());
        existing.setAddress(profile.getAddress());
        existing.setStatus(profile.getStatus());

        return repository.save(existing);
    }

    // ---------------- Fetch methods ----------------
    public Optional<CustomerProfile> getProfileByUserId(int userID) {
        return repository.findByUser_Id(userID);
    }

    public Optional<CustomerProfile> readProfile(int profileID) {
        return repository.findById(profileID);
    }

    public void deleteProfile(int profileID) {
        repository.deleteById(profileID);
    }

    public List<CustomerProfile> getAllProfiles() {
        return repository.findAll();
    }

    public List<CustomerProfile> findProfilesByStatus(String status) {
        return repository.findByStatus(status);
    }
}

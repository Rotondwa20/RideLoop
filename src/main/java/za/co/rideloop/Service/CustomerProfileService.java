package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Repository.CustomerProfileRepository;
import za.co.rideloop.Repository.UserRepository;

import java.util.List;

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

    // ---------------- Create a profile (admin/other) ----------------
    public CustomerProfile createProfile(CustomerProfile profile) {
        return repository.save(profile);
    }

    // ---------------- Create or update a profile for logged-in user ----------------
    public CustomerProfile saveProfileForUser(int userID, CustomerProfile profile) {
        User user = getUserById(userID); // use the method above
        profile.setUser(user);

        repository.findByUser_Id(userID).ifPresent(existingProfile ->
                profile.setProfileID(existingProfile.getProfileID())
        );

        return repository.save(profile);
    }

    // ---------------- Get profile by user ID ----------------
    public CustomerProfile getProfileByUserId(int userID) {
        return repository.findByUser_Id(userID).orElse(null);
    }

    // ---------------- Get profile by profile ID ----------------
    public CustomerProfile readProfile(int id) {
        return repository.findById(id).orElse(null);
    }

    // ---------------- Update profile ----------------
    public CustomerProfile updateProfile(CustomerProfile profile) {
        return repository.save(profile);
    }

    // ---------------- Delete profile ----------------
    public void deleteProfile(int id) {
        repository.deleteById(id);
    }

    // ---------------- Get all profiles ----------------
    public List<CustomerProfile> getAllProfiles() {
        return repository.findAll();
    }
}

package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Service.CustomerProfileService;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService service;

    // ---------------- Get or create profile for the logged-in user ----------------
    @GetMapping("/me")
    public CustomerProfile getOrCreateMyProfile(@RequestParam int userID) {
        return service.getProfileByUserId(userID)
                .orElseGet(() -> {
                    User user = service.getUserById(userID);
                    CustomerProfile profile = new CustomerProfile();
                    profile.setUser(user);
                    profile.setStatus("pending"); // default status
                    return service.createProfileForUser(profile, userID);
                });
    }

    // ---------------- Update profile for the logged-in user (cannot change status) ----------------
    @PutMapping("/me")
    public CustomerProfile updateMyProfile(@RequestParam int userID, @RequestBody CustomerProfile profile) {
        profile.setUser(service.getUserById(userID)); // ensure correct user
        return service.updateProfile(profile, false); // false = not admin
    }

    // ---------------- Admin: update status only ----------------
    @PutMapping("/{id}/status")
    public CustomerProfile updateProfileStatus(@PathVariable int id, @RequestParam String status) {
        CustomerProfile profile = service.readProfile(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + id));
        profile.setStatus(status);
        return service.updateProfile(profile, true); // true = admin
    }

    // ---------------- Admin: create profile ----------------
    @PostMapping("/admin")
    public CustomerProfile createProfileForAdmin(@RequestBody CustomerProfile profile) {
        return service.createProfileForUser(profile, profile.getUser().getUserID());
    }

    @PutMapping("/{id}")
    public CustomerProfile updateProfile(@PathVariable int id, @RequestBody CustomerProfile profile) {
        profile.setProfileID(id); // ensure ID is correct
        return service.updateProfile(profile, false); // false = not admin
    }

    // ---------------- Admin: get all profiles ----------------
    @GetMapping
    public List<CustomerProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    // ---------------- Admin: get profile by ID ----------------
    @GetMapping("/{id}")
    public CustomerProfile getProfileById(@PathVariable int id) {
        return service.readProfile(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + id));
    }

    // ---------------- Admin: get profiles by status ----------------
    @GetMapping("/status")
    public List<CustomerProfile> getProfilesByStatus(@RequestParam String status) {
        return service.findProfilesByStatus(status);
    }

    // ---------------- Admin: delete profile ----------------
    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable int id) {
        service.deleteProfile(id);
    }
}

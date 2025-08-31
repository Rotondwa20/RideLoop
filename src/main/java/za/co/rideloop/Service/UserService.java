package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Repository.CustomerRewardsRepository;
import za.co.rideloop.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final CustomerRewardsRepository rewardsRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${admin.security.code}")
    private String adminSecurityCode;

    @Autowired
    public UserService(UserRepository repository, CustomerRewardsRepository rewardsRepository) {
        this.repository = repository;
        this.rewardsRepository = rewardsRepository;
    }

    // -----------------------
    // Register user (Customer or Admin)
    public String registerUser(User user, String securityCode) {

        Optional<User> existingUser = repository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email already exists";
        }

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            if (securityCode == null || !securityCode.equals(adminSecurityCode)) {
                return "Invalid security code for ADMIN registration";
            }
        }

        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return "User registered successfully";
    }

    // -----------------------
    // Get all users
    public List<User> findAll() {
        return repository.findAll();
    }

    // -----------------------
    // Find user by ID
    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    // -----------------------
    // Delete user
    public void deleteById(int id) {
        rewardsRepository.deleteByUserUserID(id);
        repository.deleteById(id);
    }

    // -----------------------
    // Login and return User object if successful
    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOpt = repository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return Optional.empty(); // user not found
        }

        User user = userOpt.get();

        // check password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return Optional.empty(); // invalid password
        }

        return Optional.of(user); // success
    }

}

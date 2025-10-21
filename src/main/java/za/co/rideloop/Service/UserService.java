package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.security.code}")
    private String adminSecurityCode;

    // ✅ Constructor injection
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register user
    public String registerUser(User user, String securityCode) {
        // Check if email already exists
        Optional<User> existingUser = repository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email already exists";
        }

        // Check admin security code
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            if (securityCode == null || !securityCode.equals(adminSecurityCode)) {
                return "Invalid security code for ADMIN registration";
            }
        }

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return "User registered successfully";
    }

    // ✅ Get all users
    public List<User> findAll() {
        return repository.findAll();
    }

    // ✅ Find user by ID
    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    // ✅ Delete user by ID
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    // ✅ Login user
    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOpt = repository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        User user = userOpt.get();

        // Check password matches encoded password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return Optional.empty();
        }

        return Optional.of(user);
    }
}

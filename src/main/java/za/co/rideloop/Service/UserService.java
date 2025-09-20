package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${admin.security.code}")
    private String adminSecurityCode;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

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

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return "User registered successfully";
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    // User login
    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOpt = repository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return Optional.empty();
        }

        return Optional.of(user);
    }
}

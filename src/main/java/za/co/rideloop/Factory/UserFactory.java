package za.co.rideloop.Factory;

import za.co.rideloop.Domain.User;

public class UserFactory {


    public static User createCustomer(String username, String email, String password) {
        return new User.Builder()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password)
                .setRole("CUSTOMER")
                .build();
    }


    public static User createAdmin(String username, String email, String password) {
        return new User.Builder()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password)
                .setRole("ADMIN")
                .build();
    }
}

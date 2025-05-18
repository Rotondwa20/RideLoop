package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Admin;

/**
 * AdminFactory.java
 * AdminFactory Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
public class AdminFactory {


    public static Admin createAdmin(
    int adminID,
    String userName,
    String password){
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("userName cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }

        return new Admin.adminBuilder()
                .setAdminID(adminID)
                .setUserName(userName)
                .setPassword(password)
                .build();
        
    }

}

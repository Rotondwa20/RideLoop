package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AdminFactoryTest.java
 * AdminFactoryTest Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
class AdminFactoryTest {
    @Test
    public void testCreateAdminSuccess() {

        Admin admin = AdminFactory.createAdmin(1, "adminUser", "securePass123");


        assertNotNull(admin);
        assertEquals(1, admin.getAdminID());
        assertEquals("adminUser", admin.getUserName());
        assertEquals("securePass123", admin.getPassword());
    }

   

}
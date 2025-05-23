package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.CustomerApproval;
import za.co.rideloop.Factory.CustomerApprovalFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CustomerApprovalFactoryTest.java
 * CustomerApprovalFactoryTest Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
class CustomerApprovalFactoryTest {

    @Test
    public void testCreateCustomerApprovalSuccess() {

        CustomerApproval approval = CustomerApprovalFactory.createCustomerApproval(1, "ABC123", true);


        assertNotNull(approval);
        assertEquals(1, approval.getCustomerApprovalId());
        assertEquals("ABC123", approval.getLicenseNumber());
        assertTrue(approval.isApproval());
    }

}
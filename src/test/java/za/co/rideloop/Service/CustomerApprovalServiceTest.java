/**
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/

package za.co.rideloop.Service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.CustomerApproval;
import za.co.rideloop.Factory.CustomerApprovalFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerApprovalServiceTest {

    @Autowired
    private CustomerApprovalService service;
    private CustomerApproval approval;

    @BeforeEach
    void setUp() {
        this.approval = CustomerApprovalFactory.createCustomerApproval(1, "ABC333", true);
    }

    @Test
    @Order(1)
    void save() {
        CustomerApproval createApproval = this.service.save(this.approval);
        Assertions.assertNotNull(createApproval);
        System.out.println(createApproval);
    }

    @Test
    @Order(2)
    void read() {
        CustomerApproval readApproval = this.service.read(2);
        Assertions.assertNotNull(readApproval);
        System.out.println(readApproval);
    }

//    @Test
//    @Order(3)
//    void update() {
//        CustomerApproval newAproval = (new CustomerApproval.approvalBuilder()).setLicenseNumber("34567").build();
//        CustomerApproval updateApproval = this.service.update(newAproval);
//        Assertions.assertNotNull(updateApproval);
//        System.out.println(updateApproval);
//    }
@Test
@Order(3)
void update() {
    CustomerApproval updated = new CustomerApproval.approvalBuilder()
            .copy(this.approval)
            .setLicenseNumber("XYZ999")
            .build();

    CustomerApproval result = this.service.update(updated);
    assertNotNull(result);
    assertEquals("XYZ999", result.getLicenseNumber());
    System.out.println("Updated: " + result);
}
    @Test
    @Order(4)
    void deleteById() {
        boolean deleted = this.service.deleteById(8);
        Assertions.assertTrue(deleted);
    }

    @Test
    @Order(5)
    void getAll() {
        List<CustomerApproval> customerApprovals = this.service.getall();
        System.out.println(customerApprovals);
    }


}
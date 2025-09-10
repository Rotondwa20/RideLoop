/**
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/

package za.co.rideloop.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.co.rideloop.Domain.CustomerApproval;
import za.co.rideloop.Factory.CustomerApprovalFactory;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CustomerApprovalControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String BASE_URL = "http://localhost:8080/rideloop/customerApproval";
    private CustomerApproval customerApproval;

    @BeforeEach
    void setUp() {
        this.customerApproval = CustomerApprovalFactory.createCustomerApproval(1, "ABC123", true);
    }

    @Test
    void save() {
        String url = "http://localhost:8080/rideloop/customerApproval/save";
        ResponseEntity<CustomerApproval> postResponse = this.testRestTemplate.postForEntity(url, this.customerApproval, CustomerApproval.class, new Object[0]);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
        System.out.println("Customer approval saved:");
        System.out.println(postResponse.getBody());
    }

    @Test
    void read() {
        String url = "http://localhost:8080/rideloop/customerApproval/read/2";
        System.out.println(url);
        ResponseEntity<CustomerApproval> response = this.testRestTemplate.getForEntity(url, CustomerApproval.class, new Object[0]);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    void update() {
        String url = "http://localhost:8080/rideloop/customerApproval/update";
        CustomerApproval newCustomerApproval = (new CustomerApproval.approvalBuilder()).copy(this.customerApproval).setLicenseNumber("878").build();
        ResponseEntity<CustomerApproval> postResponse = this.testRestTemplate.postForEntity(url, newCustomerApproval, CustomerApproval.class, new Object[0]);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
        System.out.println(postResponse.getBody());
    }

    @Test
    void delete() {
        String url = "http://localhost:8080/rideloop/customerApproval/delete/1";
        this.testRestTemplate.delete(url, new Object[0]);
        System.out.println("Customer approval Deleted successfully");
    }

    @Test
    void getAll() {
        String url = "http://localhost:8080/rideloop/customerApproval/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity((Object)null, headers);
        ResponseEntity<String> response = this.testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, new Object[0]);
        System.out.println("All Customer approvals:");
        System.out.println((String)response.getBody());
    }
}
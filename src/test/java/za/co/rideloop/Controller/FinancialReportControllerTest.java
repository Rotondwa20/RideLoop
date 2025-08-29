/**
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/

package za.co.rideloop.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.co.rideloop.Domain.FinancialReport;
import za.co.rideloop.Factory.FinancialReportFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FinancialReportControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String BASE_URL = "http://localhost:8080/rideloop/financialReport";
    private FinancialReport financialReport;

    @BeforeEach
    void setUp() {
        this.financialReport = FinancialReportFactory.createFinancialReport(1, "2025-05-18", "Monthly", "PDF", (double)10000.0F, (double)7000.0F, (double)1000.0F, (double)2000.0F, (double)5000.0F, (double)5000.0F);
    }

    @Test
    @Order(1)
    void save() {
        String url = "http://localhost:8080/rideloop/financialReport/save";
        ResponseEntity<FinancialReport> postResponse = this.testRestTemplate.postForEntity(url, this.financialReport, FinancialReport.class, new Object[0]);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
        System.out.println("Financial report saved:");
        System.out.println(postResponse.getBody());
    }

    @Test
    @Order(2)
    void read() {
        String url = "http://localhost:8080/rideloop/financialReport/read/2";
        ResponseEntity<FinancialReport> response = this.testRestTemplate.getForEntity(url, FinancialReport.class, new Object[0]);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        String url = "http://localhost:8080/rideloop/financialReport/update";
        FinancialReport newFinancialReport = (new FinancialReport.Builder()).copy(this.financialReport).setRentalIncome((double)5000.0F).build();
        ResponseEntity<FinancialReport> postResponse = this.testRestTemplate.postForEntity(url, newFinancialReport, FinancialReport.class, new Object[0]);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
        System.out.println(postResponse.getBody());
    }

    @Test
    @Order(4)
    void delete() {
        String url = "http://localhost:8080/rideloop/financialReport/delete/4";
        this.testRestTemplate.delete(url, new Object[0]);
        System.out.println("Financial report Deleted successfully");
    }

    @Test
    @Order(5)
    void getAll() {
        String url = "http://localhost:8080/rideloop/financialReport/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity((Object)null, headers);
        ResponseEntity<String> response = this.testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, new Object[0]);
        System.out.println("All Financial reports:");
        System.out.println((String)response.getBody());
    }
}
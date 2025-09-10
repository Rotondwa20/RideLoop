/**
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/

package za.co.rideloop.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.FinancialReport;
import za.co.rideloop.Factory.FinancialReportFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class FinancialReportServiceTest {

    @Autowired
    private FinancialReportService financialReportService;
    public FinancialReport financialReport;

    @BeforeEach
    void setUp() {
        this.financialReport = FinancialReportFactory.createFinancialReport(1, "2025-05-18", "Monthly", "PDF", (double)10000.0F, (double)7000.0F, (double)1000.0F, (double)2000.0F, (double)5000.0F, (double)5000.0F);
    }

    @Test
    void save() {
        FinancialReport newFinancialReport = this.financialReportService.save(this.financialReport);
        Assertions.assertNotNull(newFinancialReport);
        System.out.println(newFinancialReport);
    }

    @Test
    void read() {
        FinancialReport readFinancialReport = this.financialReportService.read(1);
        Assertions.assertNotNull(readFinancialReport);
        System.out.println(readFinancialReport);
    }

    @Test
    void update() {
        FinancialReport newFinancialReport = (new FinancialReport.Builder()).copy(this.financialReport).setRentalIncome((double)20000.0F).build();
        FinancialReport updatedFinancialReport = this.financialReportService.update(newFinancialReport);
        Assertions.assertNotNull(updatedFinancialReport);
        System.out.println(updatedFinancialReport);
    }

    @Test
    void deleteById() {
        boolean deleted = this.financialReportService.deleteById(4);
        Assertions.assertTrue(deleted);
    }

    @Test
    void getall() {
        List<FinancialReport> financialReports = this.financialReportService.getall();
        System.out.println(financialReports);
    }
}
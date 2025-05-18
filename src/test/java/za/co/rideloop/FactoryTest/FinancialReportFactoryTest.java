package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FinancialReportFactoryTest.java
 * FinancialReportFactoryTest Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
class FinancialReportFactoryTest {

    @Test
    public void testCreateFinancialReportSuccess() {
        // Test creating a FinancialReport with valid inputs
        FinancialReport report = FinancialReportFactory.createFinancialReport(
                1, "2025-05-18", "Monthly", "PDF",
                10000.0, 7000.0, 1000.0, 2000.0, 5000.0, 5000.0
        );


        assertNotNull(report);
        assertEquals(1, report.getReportID());
        assertEquals("2025-05-18", report.getGenerateDate());
        assertEquals("Monthly", report.getTimePeriod());
        assertEquals("PDF", report.getExportFormat());
        assertEquals(10000.0, report.getTotalRevenue(), 0.01);
        assertEquals(7000.0, report.getRentalIncome(), 0.01);
        assertEquals(1000.0, report.getAdditionalCharges(), 0.01);
        assertEquals(2000.0, report.getInsuranceIncome(), 0.01);
        assertEquals(5000.0, report.getExpenses(), 0.01);
        assertEquals(5000.0, report.getNetProfit(), 0.01);
    }

}
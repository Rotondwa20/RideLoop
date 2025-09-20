package za.co.rideloop.Factory;


import za.co.rideloop.Domain.FinancialReport;

/**
 * FinancialReportFactory.java
 * FinancialReportFactory Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
public class FinancialReportFactory {

    public static FinancialReport createFinancialReport(
            int reportID, String generateDate, String timePeriod,
            String exportFormat, double totalRevenue,
            double rentalIncome, double additionalCharges,
            double insuranceIncome, double expense, double netProfit) {


        if (generateDate == null || generateDate.trim().isEmpty()) {
            throw new IllegalArgumentException("generateDate cannot be null or empty");
        }
        if (timePeriod == null || timePeriod.trim().isEmpty()) {
            throw new IllegalArgumentException("timePeriod cannot be null or empty");
        }
        if (exportFormat == null || exportFormat.trim().isEmpty()) {
            throw new IllegalArgumentException("exportFormat cannot be null or empty");
        }
        if (totalRevenue < 0 || rentalIncome < 0 || additionalCharges < 0 || insuranceIncome < 0 || expense < 0 || netProfit < 0) {
            throw new IllegalArgumentException("Financial values cannot be negative");
        }

        return new FinancialReport.Builder()
                .setReportID(reportID)
                .setGenerateDate(generateDate)
                .setTimePeriod(timePeriod)
                .setExportFormat(exportFormat)
                .setTotalRevenue(totalRevenue)
                .setRentalIncome(rentalIncome)
                .setAdditionalCharges(additionalCharges)
                .setInsuranceIncome(insuranceIncome)
                .setExpenses(expense)
                .setNetProfit(netProfit)
                .build();
    }
}

package za.co.rideloop.Domain;

import jakarta.persistence.*;

/**
 * FinancialReport.java
 * FinancialReport Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
@Entity
@Table(name = "financial_reports")
public class FinancialReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportID;

    @Column(name = "generate_date", nullable = false)
    private String generateDate;

    @Column(name = "time_period", nullable = false)
    private String timePeriod;

    @Column(name = "export_format")
    private String exportFormat;

    @Column(name = "total_revenue", nullable = false)
    private double totalRevenue;

    @Column(name = "rental_income", nullable = false)
    private double rentalIncome;

    @Column(name = "additional_charges")
    private double additionalCharges;

    @Column(name = "insurance_income")
    private double insuranceIncome;

    @Column(name = "expenses", nullable = false)
    private double expenses;

    @Column(name = "net_profit", nullable = false)
    private double netProfit;

    protected FinancialReport(){

    }

    private FinancialReport(FinancialReport.Builder builder) {
        this.reportID = builder.reportID;
        this.generateDate = builder.generateDate;
        this.timePeriod = builder.timePeriod;
        this.exportFormat = builder.exportFormat;
        this.totalRevenue = builder.totalRevenue;
        this.rentalIncome = builder.rentalIncome;
        this.additionalCharges = builder.additionalCharges;
        this.insuranceIncome = builder.insuranceIncome;
        this.expenses = builder.expenses;
        this.netProfit = builder.netProfit;
    }

    public int getReportID() {
        return reportID;
    }

    public String getGenerateDate() {
        return generateDate;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public String getExportFormat() {
        return exportFormat;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getRentalIncome() {
        return rentalIncome;
    }

    public double getAdditionalCharges() {
        return additionalCharges;
    }

    public double getInsuranceIncome() {
        return insuranceIncome;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getNetProfit() {
        return netProfit;
    }




    public static class Builder {
        private int reportID;
        private String generateDate;
        private String timePeriod;
        private String exportFormat;
        private double totalRevenue;
        private double rentalIncome;
        private double additionalCharges;
        private double insuranceIncome;
        private double expenses;
        private double netProfit;

        public Builder() {

        }

        public Builder(int reportID, String generateDate, String timePeriod, String exportFormat, double totalRevenue,
                       double rentalIncome, double additionalCharges, double insuranceIncome, double expenses, double netProfit) {
            this.reportID = reportID;
            this.generateDate = generateDate;
            this.timePeriod = timePeriod;
            this.exportFormat = exportFormat;
            this.totalRevenue = totalRevenue;
            this.rentalIncome = rentalIncome;
            this.additionalCharges = additionalCharges;
            this.additionalCharges = additionalCharges;
            this.insuranceIncome = insuranceIncome;
            this.insuranceIncome = insuranceIncome;
            this.expenses = expenses;
            this.expenses = expenses;
            this.netProfit = netProfit;

        }

        public Builder setReportID(int reportID) {
            this.reportID = reportID;
            return this;
        }

        public Builder setGenerateDate(String generateDate) {
            this.generateDate = generateDate;
            return this;
        }

        public Builder setTimePeriod(String timePeriod) {
            this.timePeriod = timePeriod;
            return this;
        }

        public Builder setExportFormat(String exportFormat) {
            this.exportFormat = exportFormat;
            return this;
        }

        public Builder setTotalRevenue(double totalRevenue) {
            this.totalRevenue = totalRevenue;
            return this;
        }

        public Builder setRentalIncome(double rentalIncome) {
            this.rentalIncome = rentalIncome;
            return this;
        }

        public Builder setAdditionalCharges(double additionalCharges) {
            this.additionalCharges = additionalCharges;
            return this;
        }

        public Builder setInsuranceIncome(double insuranceIncome) {
            this.insuranceIncome = insuranceIncome;
            return this;
        }

        public Builder setExpenses(double expenses) {
            this.expenses = expenses;
            return this;
        }

        public Builder setNetProfit(double netProfit) {
            this.netProfit = netProfit;
            return this;
        }

        public Builder copy(FinancialReport financialReport) {
            this.reportID = financialReport.reportID;
            this.generateDate = financialReport.generateDate;
            this.timePeriod = financialReport.timePeriod;
            this.exportFormat = financialReport.exportFormat;
            this.totalRevenue = financialReport.totalRevenue;
            this.rentalIncome = financialReport.rentalIncome;
            this.additionalCharges = financialReport.additionalCharges;
            this.insuranceIncome = financialReport.insuranceIncome;
            this.expenses = financialReport.expenses;
            this.netProfit = financialReport.netProfit;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "reportID=" + reportID +
                    ", generateDate='" + generateDate + '\'' +
                    ", timePeriod='" + timePeriod + '\'' +
                    ", exportFormat='" + exportFormat + '\'' +
                    ", totalRevenue=" + totalRevenue +
                    ", rentalIncome=" + rentalIncome +
                    ", additionalCharges=" + additionalCharges +
                    ", insuranceIncome=" + insuranceIncome +
                    ", expenses=" + expenses +
                    ", netProfit=" + netProfit +
                    '}';
        }


        public FinancialReport build() {

            return new FinancialReport(this);

        }
    }
}


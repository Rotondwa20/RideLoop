package za.co.rideloop.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "security_companies") // safer naming
public class SecurityCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int securityCompanyID;

    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String serviceType;
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate contractStartDate;
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate  contractEndDate;

    private double monthlyFee;
    private String emergencyHotline;
    private String coverageArea;

    // === Constructors ===
    public SecurityCompany() {}

    private SecurityCompany(Builder builder) {
        this.securityCompanyID = builder.securityCompanyID;
        this.name = builder.name;
        this.contactPerson = builder.contactPerson;
        this.phone = builder.phone;
        this.email = builder.email;
        this.serviceType = builder.serviceType;
        this.contractStartDate = builder.contractStartDate;
        this.contractEndDate = builder.contractEndDate;
        this.monthlyFee = builder.monthlyFee;
        this.emergencyHotline = builder.emergencyHotline;
        this.coverageArea = builder.coverageArea;
    }

    // === Getters ===
    public int getSecurityCompanyID() { return securityCompanyID; }
    public String getName() { return name; }
    public String getContactPerson() { return contactPerson; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getServiceType() { return serviceType; }
    public LocalDate  getContractStartDate() { return contractStartDate; }
    public LocalDate  getContractEndDate() { return contractEndDate; }
    public double getMonthlyFee() { return monthlyFee; }
    public String getEmergencyHotline() { return emergencyHotline; }
    public String getCoverageArea() { return coverageArea; }



    @Override
    public int hashCode() {
        return Objects.hash(securityCompanyID, name, contactPerson, phone, email,
                serviceType, contractStartDate, contractEndDate,
                monthlyFee, emergencyHotline, coverageArea);
    }

    @Override
    public String toString() {
        return "SecurityCompany{" +
                "securityCompanyID=" + securityCompanyID +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", contractStartDate=" + contractStartDate +
                ", contractEndDate=" + contractEndDate +
                ", monthlyFee=" + monthlyFee +
                ", emergencyHotline='" + emergencyHotline + '\'' +
                ", coverageArea='" + coverageArea + '\'' +
                '}';
    }

    // === Builder ===
    public static class Builder {
        private int securityCompanyID;
        private String name;
        private String contactPerson;
        private String phone;
        private String email;
        private String serviceType;
        private LocalDate  contractStartDate;
        private LocalDate  contractEndDate;
        private double monthlyFee;
        private String emergencyHotline;
        private String coverageArea;

        public Builder securityCompanyID(int id) { this.securityCompanyID = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder contactPerson(String contactPerson) { this.contactPerson = contactPerson; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder serviceType(String serviceType) { this.serviceType = serviceType; return this; }
        public Builder contractStartDate(LocalDate  date) { this.contractStartDate = date; return this; }
        public Builder contractEndDate(LocalDate  date) { this.contractEndDate = date; return this; }
        public Builder monthlyFee(double fee) { this.monthlyFee = fee; return this; }
        public Builder emergencyHotline(String hotline) { this.emergencyHotline = hotline; return this; }
        public Builder coverageArea(String area) { this.coverageArea = area; return this; }

        public SecurityCompany build() { return new SecurityCompany(this); }
    }
}

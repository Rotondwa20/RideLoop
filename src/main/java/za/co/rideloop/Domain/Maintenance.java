package za.co.rideloop.Domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/* Maintenance.java

     Maintenance POJO class

     Author: Natasha Njili(221785345)

     Date: 11 May 2025 */
@Entity
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String insuranceCompanyName;
    private String contactPerson;
    private String contactNumber;
    private String coverageType;
    private Double costPerMonth;
    private String description;

    protected Maintenance() {
    }

    private Maintenance(Builder builder) {
        this.id = builder.id;
        this.insuranceCompanyName = builder.insuranceCompanyName;
        this.contactPerson = builder.contactPerson;
        this.contactNumber = builder.contactNumber;
        this.coverageType = builder.coverageType;
        this.costPerMonth = builder.costPerMonth;
        this.description = builder.description;
    }

    public Long getId() {
        return id;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public Double getCostPerMonth() {
        return costPerMonth;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private Long id;
        private String insuranceCompanyName;
        private String contactPerson;
        private String contactNumber;
        private String coverageType;
        private Double costPerMonth;
        private String description;


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        public Builder setInsuranceCompanyName(String insuranceCompanyName) {
            this.insuranceCompanyName = insuranceCompanyName;
            return this;
        }

        public Builder setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder setCoverageType(String coverageType) {
            this.coverageType = coverageType;
            return this;
        }

        public Builder setCostPerMonth(Double costPerMonth) {
            this.costPerMonth = costPerMonth;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Maintenance build() {
            return new Maintenance(this);
        }
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "insuranceCompanyName='" + insuranceCompanyName + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", coverageType='" + coverageType + '\'' +
                ", costPerMonth=" + costPerMonth +
                ", description='" + description + '\'' +
                '}';
    }
}

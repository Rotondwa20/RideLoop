package za.co.rideloop.Factory;

import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Util.ValidationHelper;

import java.time.LocalDate;
/**
*SecurityCompanyFactory.java
*SecurityCompanyFactory model class
*@author : Swatsi Bongani Ratia
*@studnr : 230724477
*@group  : 3I
*@Java version: "21.0.3" 2024-04-16 LTS
*/

public class SecurityCompanyFactory {
    public static SecurityCompany build(
            String name,
            String contactPerson,
            String phone,
            String email,
            String serviceType,
            LocalDate contractStartDate,
            LocalDate contractEndDate,
            double monthlyFee,
            String emergencyHotline,
            String coverageArea
    ) {
        // === Validations ===
        ValidationHelper.requireNonBlank(name, "Company name");
        ValidationHelper.requireNonBlank(contactPerson, "Contact person");
        ValidationHelper.requireNonBlank(phone, "Phone number");
        ValidationHelper.requireNonBlank(email, "Email address");
        ValidationHelper.requireNonBlank(serviceType, "Service type");
        ValidationHelper.requireNonNull(contractStartDate, "Contract start date");
        ValidationHelper.requireNonNull(contractEndDate, "Contract end date");

        if (contractEndDate.isBefore(contractStartDate)) {
            throw new IllegalArgumentException("Contract end date cannot be before start date");
        }

        if (monthlyFee < 0) {
            throw new IllegalArgumentException("Monthly fee cannot be negative");
        }

        // === Build object ===
        return new SecurityCompany.Builder()
                .name(name.trim())
                .contactPerson(contactPerson.trim())
                .phone(phone.trim())
                .email(email.trim())
                .serviceType(serviceType.trim())
                .contractStartDate(contractStartDate)
                .contractEndDate(contractEndDate)
                .monthlyFee(monthlyFee)
                .emergencyHotline(emergencyHotline != null ? emergencyHotline.trim() : null)
                .coverageArea(coverageArea != null ? coverageArea.trim() : null)
                .build();
    }
}

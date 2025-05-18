package za.co.rideloop.Factory;

import za.co.rideloop.Domain.SecurityCompany;

import java.util.Date;

/**
 * SecurityCompanyFactory.java
 * SecurityCompanyFactory model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */

public class SecurityCompanyFactory {
    public static SecurityCompany build(
            int securityCompanyID,
            String name,
            String contactPerson,
            String phone,
            String email,
            String serviceType,
            Date contractStartDate,
            Date contractEndDate,
            double monthlyFee,
            String emergencyHotline,
            String coverageArea
    ) {
        if (name == null || name.isEmpty() || contactPerson == null || contactPerson.isEmpty() ||
                phone == null || phone.isEmpty() || email == null || email.isEmpty() ||
                serviceType == null || serviceType.isEmpty() || emergencyHotline == null || emergencyHotline.isEmpty() ||
                coverageArea == null || coverageArea.isEmpty()) {
            throw new IllegalArgumentException("All fields must be provided and not empty");
        }

        if (contractStartDate == null || contractEndDate == null || contractEndDate.before(contractStartDate)) {
            throw new IllegalArgumentException("Invalid contract dates");
        }

        if (monthlyFee < 0) {
            throw new IllegalArgumentException("Monthly fee cannot be negative");
        }

        return new SecurityCompany.Builder()
                .securityCompanyID(securityCompanyID)
                .name(name)
                .contactPerson(contactPerson)
                .phone(phone)
                .email(email)
                .serviceType(serviceType)
                .contractStartDate(contractStartDate)
                .contractEndDate(contractEndDate)
                .monthlyFee(monthlyFee)
                .emergencyHotline(emergencyHotline)
                .coverageArea(coverageArea)
                .build();
    }
}


package za.co.rideloop.Factory;

import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Util.ValidationHelper;

import java.time.LocalDate;
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
            String name,
            String contactPerson,
            String phone,
            String email,
            String serviceType,
            LocalDate contractStartDate,
            LocalDate  contractEndDate,
            double monthlyFee,
            String emergencyHotline,
            String coverageArea
    ) {
        ValidationHelper.requireNonBlank(name, "name");

        if (contractStartDate == null || contractEndDate == null || contractEndDate.isBefore(contractStartDate)) {
            throw new IllegalArgumentException("Invalid contract dates");
        }

        if (monthlyFee < 0) {
            throw new IllegalArgumentException("Monthly fee cannot be negative");
        }

        return new SecurityCompany.Builder()
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


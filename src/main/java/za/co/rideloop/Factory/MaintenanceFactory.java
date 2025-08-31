package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Maintenance;
/* MaintananceFactory.java

     Mainntanence Factory class

     Author: Natasha Njili(221785345)

     Date: 18 May 2025 */
public class MaintenanceFactory {

    public static Maintenance createMaintenance(
            Long id,
    String insuranceCompanyName,
    String contactPerson,
    String contactNumber,
    String coverageType,
    Double costPerMonth,
    String description
    ) {
        return new Maintenance.Builder()
            .setId(id)
            .setInsuranceCompanyName(insuranceCompanyName)
            .setContactPerson(contactPerson)
            .setContactNumber(contactNumber)
            .setCoverageType(coverageType)
            .setCostPerMonth(costPerMonth)
            .setDescription(description)
            .build();
    }
}

package za.co.rideloop.Factory;

import za.co.rideloop.Domain.CustomerApproval;

/**
 * CustomerApprovalFactory.java
 * CustomerApprovalFactory Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
public class CustomerApprovalFactory {
    public static CustomerApproval createCustomerApproval(
            int customerApprovalId, String licenseNumber, boolean approval) {


        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("licenseNumber cannot be null or empty");
        }

        return new CustomerApproval.approvalBuilder()
                .setCustomerApprovalId(customerApprovalId)
                .setLicenseNumber(licenseNumber)
                .setApproval(approval)
                .build();
    }
}

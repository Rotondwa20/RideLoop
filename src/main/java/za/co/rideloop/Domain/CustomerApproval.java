package za.co.rideloop.Domain;

import jakarta.persistence.*;

/**
 * CustomerApproval.java
 * CustomerApproval Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
@Entity
@Table(name = "customer_approvals")
public class CustomerApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_approval_id")
    private int customerApprovalId;

    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @Column(name = "approval", nullable = false)
    private boolean approval;


    protected CustomerApproval(){

    }
    private  CustomerApproval(CustomerApproval.approvalBuilder builder) {
        this.customerApprovalId= builder.customerApprovalId;
        this.licenseNumber = builder.licenseNumber;
        this.approval = builder.approval;



    }

    public int getCustomerApprovalId() {
        return customerApprovalId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public boolean isApproval() {
        return approval;
    }




    public static class approvalBuilder {
        private int customerApprovalId;
        private String licenseNumber;
        private boolean approval;

        public approvalBuilder(){

        }
        public approvalBuilder(int customerApprovalId, String licenseNumber, boolean approval) {
            this.customerApprovalId = customerApprovalId;
            this.licenseNumber = licenseNumber;
            this.licenseNumber = licenseNumber;
            this.approval = approval;

        }

        public approvalBuilder setCustomerApprovalId(int customerApprovalId) {
            this.customerApprovalId = customerApprovalId;
            return this;
        }

        public approvalBuilder setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        public approvalBuilder setApproval(boolean approval) {
            this.approval = approval;
            return this;
        }

        public approvalBuilder copy(CustomerApproval customerApproval){
            this.customerApprovalId = customerApproval.customerApprovalId;
            this.licenseNumber = customerApproval.licenseNumber;
            this.approval = customerApproval.approval;
            return this;
        }



        public CustomerApproval build(){
            return new CustomerApproval(this);
        }
    }

    @Override
    public String toString() {
        return "CustomerApproval{" +
                "customerApprovalId=" + customerApprovalId +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", approval=" + approval +
                '}';
    }
}



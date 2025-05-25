package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.CustomerApproval;

/**
 * CustomerApprovalRepository.java
 * CustomerApprovalRepository Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 *
 **/
@Repository
public interface CustomerApprovalRepository extends JpaRepository<CustomerApproval, Integer> {

}

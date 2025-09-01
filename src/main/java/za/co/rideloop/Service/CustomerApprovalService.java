/**
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/

package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerApproval;
import za.co.rideloop.Repository.CustomerApprovalRepository;

import java.util.List;

@Service
public class CustomerApprovalService {

    @Autowired
    private CustomerApprovalRepository repository;

    public CustomerApproval save(CustomerApproval approval) {
        return repository.save(approval);
    }

    public CustomerApproval read(int id) {
        return repository.findById(id).orElse(null);
    }

    public CustomerApproval update(CustomerApproval approval) {
        return repository.save(approval);
    }

    public boolean deleteById(int id) {
        repository.deleteById(id);
        return !repository.existsById(id);

    }

    public List<CustomerApproval> getall() {
        return repository.findAll();
    }

}

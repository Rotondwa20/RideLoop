package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Repository.CustomerRepository;

import java.util.List;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;


    public Customer createCustomer(Customer customer) {
        return this.repository.save(customer);
    }


    public Customer readCustomer(Long id) {
        return this.repository.findById(id).orElse(null);
    }


    public Customer updateCustomer(Customer customer) {
        return this.repository.save(customer);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }


    public List<Customer> getAllCustomers() {
        return this.repository.findAll();
    }


}

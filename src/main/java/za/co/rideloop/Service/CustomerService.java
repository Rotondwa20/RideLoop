package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Repository.CustomerRepository;
import za.co.rideloop.Service.Imp.ICustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerID) {
        return customerRepository.findById(customerID);
    }

    @Override
    public Customer updateCustomer(Long customerID, Customer customer) {
        Customer existing = customerRepository.findById(customerID)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerID));

        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        existing.setLicenseNumber(customer.getLicenseNumber());
        existing.setUsername(customer.getUsername());
        existing.setPassword(customer.getPassword());
        existing.setStatus(customer.getStatus());
        existing.setAddress(customer.getAddress());
        existing.setContactDetails(customer.getContactDetails());

        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Long customerID) {
        Customer existing = customerRepository.findById(customerID)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerID));
        customerRepository.delete(existing);
    }

    @Override
    public Customer create(Customer customer) {
        return createCustomer(customer);
    }

    @Override
    public Customer read(Long customerID) {
        return getCustomerById(customerID)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerID));
    }

    @Override
    public Customer update(Customer customer) {
        if (customer.getCustomerID() == null) {
            throw new RuntimeException("Customer ID must not be null for update");
        }
        return updateCustomer(customer.getCustomerID(), customer);
    }
}
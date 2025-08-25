package za.co.rideloop.Service.Imp;

import za.co.rideloop.Domain.Customer;
import za.co.rideloop.Service.IService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IService<Customer, Long> {
    Customer createCustomer(Customer customer);
    List<Customer> getAll();
    Optional<Customer> getCustomerById(Long customerID);
    Customer updateCustomer(Long customerID, Customer customer);
    void deleteCustomer(Long customerID);
}
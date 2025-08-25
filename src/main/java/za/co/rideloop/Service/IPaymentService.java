package za.co.rideloop.Service;


import za.co.rideloop.Domain.Payment;

import java.util.List;
/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
public interface IPaymentService extends  IService<Payment,Integer>{
    List<Payment> getAll();
    void delete(int id);
}

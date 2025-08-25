package za.co.rideloop.Service;

import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Domain.Rental;

import java.util.List;
/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
public interface IRentalService extends IService<Rental,Integer>{

    List<Rental> getAll();
    void delete(int id);
}

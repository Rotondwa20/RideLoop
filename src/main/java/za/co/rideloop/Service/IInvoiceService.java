package za.co.rideloop.Service;

import za.co.rideloop.Domain.Invoice;

import java.util.List;
/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
public interface IInvoiceService  extends IService<Invoice,Integer>{
    List<Invoice> getAll();
    void delete(int id);

}

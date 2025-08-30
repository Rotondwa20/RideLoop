package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Invoice;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Repository.RentalRepository;
import za.co.rideloop.Util.Helper;

import java.util.List;

/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
@Service
public class RentalService {


    @Autowired
    private RentalRepository repository;

    // ===== Create =====
    public Rental create(Rental rental) {

        if (rental == null ||
                //  !Helper.isValidId(rental.getRentalID()) ||
                //    !Helper.isValidId(rental.getCustomerID()) ||
                //    !Helper.isValidId(rental.getCarID()) ||
                rental.getStartDate() == null ||
                rental.getEndDate() == null ||
                //   !Helper.isValidDateRange(rental.getStartDate(), rental.getEndDate()) ||
                Helper.isNullOrEmpty(rental.getPickupLocation()) ||
                Helper.isNullOrEmpty(rental.getDropoffLocation()) ||
                !Helper.isValidAmount(rental.getTotalCost()) ||
                Helper.isNullOrEmpty(rental.getStatus())) {
            return null;
        }
        return this.repository.save(rental);
    }
    // ===== Create =====

    public Rental read(Integer id) {
        return repository.findById(id).orElse(null);
    }


    // ===== Update =====


    public Rental update(Rental rental) {
        // 1. Check if the input rental object is null or has no ID.
        if (rental == null || rental.getRentalID() == 0) {
            return null;
        }

        // 2. Find the existing rental by its ID.
        Rental existingRental = repository.findById(rental.getRentalID()).orElse(null);

        // 3. If an existing rental is found, update its fields.
        if (existingRental != null) {
            // Use the builder to create a new object with updated values.
            existingRental = new Rental.RentalBuilder()
                    .setRentalID(existingRental.getRentalID()) // Keep the original ID
                    .setCarID(rental.getCarID())
                    .setCustomerID(rental.getCustomerID())
                    .setStartDate(rental.getStartDate())
                    .setEndDate(rental.getEndDate())
                    .setPickupLocation(rental.getPickupLocation())
                    .setDropoffLocation(rental.getDropoffLocation())
                    .setInsuranceID(rental.getInsuranceID())
                    .setTotalCost(rental.getTotalCost())
                    .setStatus(rental.getStatus())
                    .build();

            // 4. Save the new, updated rental object.
            return repository.save(existingRental);
        } else {
            // 5. If no existing rental is found, return null.
            return null;
        }
    }


    // ===== Get All =====

    public List<Rental> getAll() {
        return this.repository.findAll();
    }
    // ===== Delete =====

    public void delete(int id) {
        this.repository.deleteById(id);
    }
    // ===== Find by Status =====
    public List<Rental> getRentalsByStatus(String status) {
        return this.repository.findByStatus(status);
    }

}

package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Rental createRental(Rental rental) {
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

    // ===== Read =====
    public Rental readRental(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    // ===== Update =====
    // In RentalService.java

    public Rental updateRental(Rental rental) {
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
//    public Rental updateRental(Rental rental) {
//        Rental existingRental = repository.findById(rental.getRentalID()).orElse(null);
//        if (existingRental != null) {
//
//            return repository.save(existingRental);
//        } else {
//            return  null;
//        }
//    }
//    public Rental updateRental(Rental rental) {
//        if (rental == null || rental.getRentalID() == null ||
//                !repository.existsById(rental.getRentalID())) {
//            return null;
//        }
//        return repository.save(rental);
//    }
//    public Rental updateRental(Rental rental) {
//        if (rental.getRentalID() == null || !repository.existsById(rental.getRentalID())) {
//            return null; // No update if it doesn't exist
//        }
//        return repository.save(rental);
//    }


//    public Rental updateRental(Rental rental) {
////        if (rental == null ||
////            //    !Helper.isValidId(rental.getRentalID()) ||
////            //    !Helper.isValidId(rental.getCustomerID()) ||
////            //    !Helper.isValidId(rental.getCarID()) ||
////                rental.getStartDate() == null ||
////                rental.getEndDate() == null ||
////              //  !Helper.isValidDateRange(rental.getStartDate(), rental.getEndDate()) ||
////                Helper.isNullOrEmpty(rental.getPickupLocation()) ||
////                Helper.isNullOrEmpty(rental.getDropoffLocation()) ||
////                !Helper.isValidAmount(rental.getTotalCost()) ||
////                Helper.isNullOrEmpty(rental.getStatus()))
////        {
////            return null;
////        }
////        return this.repository.save(rental);
//    }

    // ===== Delete =====
    public void deleteRental(Integer id) {
        this.repository.deleteById(id);
    }

    // ===== Get All =====
    public List<Rental> getAllRentals() {
        return this.repository.findAll();
    }

    // ===== Find by Status =====
    public List<Rental> getRentalsByStatus(String status) {
        return this.repository.findByStatus(status);
    }
}

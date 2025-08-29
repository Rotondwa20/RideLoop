//////package za.co.rideloop.Service;
//////
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////import za.co.rideloop.Domain.CarSupplier;
//////import za.co.rideloop.Repository.CarSupplierRepository;
//////
//////import java.util.List;
//////import java.util.Optional;
//////
///////**
////// * CarSupplierService.java
////// * CarSupplierService model class
////// *
////// * @author : Swatsi Bongani Ratia
////// * @studnr : 230724477
////// * @group : 3I
////// * @Java version: "21.0.3" 2024-04-16 LTS
////// */
//////@Service
//////public class CarSupplierService {
//////    @Autowired
//////    private CarSupplierRepository repository;
//////
//////
//////
//////    public CarSupplier createCarSupplier(CarSupplier carSupplier) {
//////        return repository.save(carSupplier);
//////    }
//////
//////    public CarSupplier readCarSupplier(int id) {
//////        return repository.findById(id).orElse(null);
//////    }
//////
//////    public CarSupplier updateCarSupplier(CarSupplier carSupplier) {
//////        if (carSupplier == null || carSupplier.getSupplierID() == 0) {
//////            return null;
//////        }
//////
//////        Optional<CarSupplier> existingSupplierOpt = repository.findById(carSupplier.getSupplierID());
//////        if (existingSupplierOpt.isPresent()) {
//////            CarSupplier existingSupplier = existingSupplierOpt.get();
//////            CarSupplier updatedSupplier = new CarSupplier.Builder()
//////                    .supplierID(existingSupplier.getSupplierID())
//////                    .name(carSupplier.getName() != null ? carSupplier.getName() : existingSupplier.getName())
//////                    .contactPerson(carSupplier.getContactPerson() != null ? carSupplier.getContactPerson() : existingSupplier.getContactPerson())
//////                    .supplyDate(carSupplier.getSupplyDate() != null ? carSupplier.getSupplyDate() : existingSupplier.getSupplyDate())
//////                    .contractStatus(carSupplier.getContractStatus() != null ? carSupplier.getContractStatus() : existingSupplier.getContractStatus())
//////                    .build();
//////            return repository.save(updatedSupplier);
//////        }
//////        return null;
//////    }
//////
//////    public void deleteCarSupplier(int id) {
//////        repository.deleteById(id);
//////    }
//////
//////    public List<CarSupplier> getAllCarSuppliers() {
//////        return repository.findAll();
//////    }
//////}
////package za.co.rideloop.Service;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////import za.co.rideloop.Domain.CarSupplier;
////import za.co.rideloop.Repository.CarSupplierRepository;
////import java.util.List;
////import java.util.Optional;
////
////@Service
////public class CarSupplierService {
////    @Autowired
////    private CarSupplierRepository repository;
////
////    public CarSupplier createCarSupplier(CarSupplier carSupplier) {
////        //return repository.save(carSupplier);
////        return repository.save(carSupplier);
////    }
////
////    public CarSupplier readCarSupplier(int id) {
////        return repository.findById(id).orElse(null);
////    }
////
////    public CarSupplier updateCarSupplier(CarSupplier carSupplier) {
////        if (carSupplier == null || carSupplier.getSupplierID() == 0) {
////            return null;
////        }
////        Optional<CarSupplier> existingSupplierOpt = repository.findById(carSupplier.getSupplierID());
////        if (existingSupplierOpt.isPresent()) {
////            CarSupplier existingSupplier = existingSupplierOpt.get();
////            CarSupplier updatedSupplier = new CarSupplier.Builder()
////                    .copy(existingSupplier) // Start with a copy of the existing entity
////                    .name(carSupplier.getName() != null ? carSupplier.getName() : existingSupplier.getName())
////                    .contactPerson(carSupplier.getContactPerson() != null ? carSupplier.getContactPerson() : existingSupplier.getContactPerson())
////                    .supplyDate(carSupplier.getSupplyDate() != null ? carSupplier.getSupplyDate() : existingSupplier.getSupplyDate())
////                    .contractStatus(carSupplier.getContractStatus() != null ? carSupplier.getContractStatus() : existingSupplier.getContractStatus())
////                    .build();
////            return repository.save(updatedSupplier);
////        }
////        return null;
////    }
////
////    public void deleteCarSupplier(int id) {
////        repository.deleteById(id);
////    }
////
////    public List<CarSupplier> getAllCarSuppliers() {
////        return repository.findAll();
////    }
////}
//package za.co.rideloop.Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import za.co.rideloop.Domain.CarSupplier;
//import za.co.rideloop.Repository.CarSupplierRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CarSupplierService {
//
//    @Autowired
//    private CarSupplierRepository repository;
//
//    // ===== CREATE =====
//    public CarSupplier createCarSupplier(CarSupplier carSupplier) {
//        return repository.save(carSupplier);
//    }
//
//    // ===== READ =====
//    public CarSupplier readCarSupplier(int id) {
//        return repository.findById(id).orElse(null);
//    }
//
//    // ===== UPDATE =====
//    public CarSupplier updateCarSupplier(CarSupplier carSupplier) {
//        if (carSupplier == null || carSupplier.getSupplierID() == 0) {
//            return null;
//        }
//
//        Optional<CarSupplier> existingSupplierOpt = repository.findById(carSupplier.getSupplierID());
//        if (existingSupplierOpt.isPresent()) {
//            CarSupplier existingSupplier = existingSupplierOpt.get();
//
//            CarSupplier updatedSupplier = new CarSupplier.Builder()
//                    .copy(existingSupplier) // start with copy
//                    .name(carSupplier.getName() != null ? carSupplier.getName() : existingSupplier.getName())
//                    .contactPerson(carSupplier.getContactPerson() != null ? carSupplier.getContactPerson() : existingSupplier.getContactPerson())
//                    .supplyDate(carSupplier.getSupplyDate() != null ? carSupplier.getSupplyDate() : existingSupplier.getSupplyDate())
//                    .contractStatus(carSupplier.getContractStatus() != null ? carSupplier.getContractStatus() : existingSupplier.getContractStatus())
//                    .build();
//
//            return repository.save(updatedSupplier);
//        }
//        return null;
//    }
//
//    // ===== DELETE =====
//    public void deleteCarSupplier(int id) {
//        repository.deleteById(id);
//    }
//
//    // ===== GET ALL =====
//    public List<CarSupplier> getAllCarSuppliers() {
//        return repository.findAll();
//    }
//}
package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CarSupplier;
import za.co.rideloop.Repository.CarSupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarSupplierService  {

    private final CarSupplierRepository repository;

    @Autowired
    public CarSupplierService(CarSupplierRepository repository) {
        this.repository = repository;
    }


    public CarSupplier createCarSupplier(CarSupplier carSupplier) {
        return repository.save(carSupplier);
    }
    public CarSupplier readCarSupplier(Integer id) {
        return repository.findById(id).orElse(null);
    }



    public CarSupplier updateCarSupplier(CarSupplier carSupplier) {
        if (carSupplier == null || carSupplier.getSupplierID() == 0) {
            return null;
        }

        Optional<CarSupplier> existingSupplierOpt = repository.findById(carSupplier.getSupplierID());
        if (existingSupplierOpt.isPresent()) {
            CarSupplier existingSupplier = existingSupplierOpt.get();

            CarSupplier updatedSupplier = new CarSupplier.Builder()
                    .supplierID(existingSupplier.getSupplierID()) // keep original ID
                    .name(carSupplier.getName() != null ? carSupplier.getName() : existingSupplier.getName())
                    .contactPerson(carSupplier.getContactPerson() != null ? carSupplier.getContactPerson() : existingSupplier.getContactPerson())
                    .supplyDate(carSupplier.getSupplyDate() != null ? carSupplier.getSupplyDate() : existingSupplier.getSupplyDate())
                    .contractStatus(carSupplier.getContractStatus() != null ? carSupplier.getContractStatus() : existingSupplier.getContractStatus())
                    .build();

            return repository.save(updatedSupplier);
        }
        return null; // not found
    }


    public List<CarSupplier> getAllCarSuppliers() {
        return repository.findAll();
    }


    public void deleteCarSupplier(int id) {
        repository.deleteById(id);
    }



}

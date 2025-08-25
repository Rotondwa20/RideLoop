package za.co.rideloop.Service.Impl;

import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CarSupplier;
import za.co.rideloop.Repository.CarSupplierRepository;
import za.co.rideloop.Service.ICarSupplierService;

import java.util.List;

/**
 * CarSupplierServiceImpl.java
 * CarSupplierServiceImpl model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@Service
public class CarSupplierServiceImpl implements ICarSupplierService {
    private final CarSupplierRepository carSupplierRepository;

    public CarSupplierServiceImpl(CarSupplierRepository carSupplierRepository) {
        this.carSupplierRepository = carSupplierRepository;
    }

    @Override
    public CarSupplier create(CarSupplier carSupplier) {
        return carSupplierRepository.save(carSupplier);
    }

    @Override
    public CarSupplier read(Integer id) {
        return carSupplierRepository.findById(id).orElse(null);
    }

    @Override
    public CarSupplier update(CarSupplier carSupplier) {
        return carSupplierRepository.findById(carSupplier.getSupplierID())
                .map(existing -> {
                    CarSupplier updated = new CarSupplier.Builder()
                            .supplierID(existing.getSupplierID())
                            .name(carSupplier.getName() != null ? carSupplier.getName() : existing.getName())
                            .contactPerson(carSupplier.getContactPerson() != null ? carSupplier.getContactPerson() : existing.getContactPerson())
                            .supplyDate(carSupplier.getSupplyDate() != null ? carSupplier.getSupplyDate() : existing.getSupplyDate())
                            .contractStatus(carSupplier.getContractStatus() != null ? carSupplier.getContractStatus() : existing.getContractStatus())
                            .build();
                    return carSupplierRepository.save(updated);
                })
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        carSupplierRepository.deleteById(id);
    }

    @Override
    public List<CarSupplier> getAll() {
        return carSupplierRepository.findAll();
    }
}

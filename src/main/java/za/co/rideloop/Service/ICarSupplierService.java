package za.co.rideloop.Service;

import za.co.rideloop.Domain.CarSupplier;

import java.util.List;

/**
 * ICarSupplierService.java
 * ICarSupplierService model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
public interface ICarSupplierService extends IService<CarSupplier, Integer> {
    List<CarSupplier> getAll();

    void delete(Integer id);
}

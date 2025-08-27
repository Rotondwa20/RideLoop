package za.co.rideloop.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {


    List<Car> findByBrandIgnoreCase(String brand);


    List<Car> findByModelIgnoreCase(String model);

}

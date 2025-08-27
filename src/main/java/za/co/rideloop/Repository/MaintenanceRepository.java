package za.co.rideloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.rideloop.Domain.Maintenance;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {


     List<Maintenance> findByInsuranceCompanyNameIgnoreCase(String insuranceCompanyName);

     List<Maintenance> findByCoverageTypeIgnoreCase(String coverageType);



}

package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Maintenance;
import za.co.rideloop.Repository.MaintenanceRepository;

import java.util.List;

@Service
public class MaintenanceService {
    private final MaintenanceRepository repository;

    @Autowired
    public MaintenanceService(MaintenanceRepository repository) {
        this.repository = repository;
    }

    public Maintenance create(Maintenance maintenance) {
        return repository.save(maintenance);
    }

    public Maintenance read(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public Maintenance update(Maintenance maintenance) {
        return repository.save(maintenance);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Maintenance> getAll() {
        return repository.findAll();
    }

    public List<Maintenance> findByInsuranceCompanyName(String insuranceCompanyName) {
        return repository.findByInsuranceCompanyNameIgnoreCase(insuranceCompanyName);
    }
    public List<Maintenance> findByCoverageType(String coverageType) {
        return repository.findByCoverageTypeIgnoreCase(coverageType);
    }
}

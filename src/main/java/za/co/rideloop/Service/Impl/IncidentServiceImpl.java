package za.co.rideloop.Service.Impl;

import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Repository.IncidentRepository;
import za.co.rideloop.Service.IIncidentService;

import java.util.List;

/**
 * IncidentServiceImpl.java
 * IncidentServiceImpl model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@Service
public class IncidentServiceImpl implements IIncidentService {
    private final IncidentRepository repository;

    public IncidentServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Incident create(Incident incident) {
        return repository.save(incident);
    }

    @Override
    public Incident read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Incident update(Incident incident) {
        return repository.findById(incident.getIncidentID())
                .map(existing -> {
                    Incident updated = new Incident.Builder()
                            .incidentID(existing.getIncidentID())
                            .incidentType(incident.getIncidentType() != null ? incident.getIncidentType() : existing.getIncidentType())
                            .description(incident.getDescription() != null ? incident.getDescription() : existing.getDescription())
                            .build();
                    return repository.save(updated);
                })
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Incident> getAll() {
        return repository.findAll();
    }
}

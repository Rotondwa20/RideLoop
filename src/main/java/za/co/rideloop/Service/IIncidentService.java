package za.co.rideloop.Service;

import za.co.rideloop.Domain.Incident;

import java.util.List;

/**
 * IIncidentService.java
 * IIncidentService model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
public interface IIncidentService extends IService<Incident, Integer> {
    List<Incident> getAll();

    void delete(Integer id);
}

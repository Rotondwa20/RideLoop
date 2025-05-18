package za.co.rideloop.factory;

import za.co.rideloop.Domain.Incident;

import java.util.Date;

/**
 * IncidentFactory.java
 * IncidentFactory model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */

public class IncidentFactory {
    public static Incident build(int incidentID, String incidentType, String description) {
        if(incidentType == null || incidentType.isBlank()||description == null || description.isBlank()){
            throw new IllegalArgumentException("invalid input - fields must not be empty");
        }

return new Incident.Builder()
        .incidentID(incidentID)
        .incidentType(incidentType)
        .description(description)
        .build();
    }
}

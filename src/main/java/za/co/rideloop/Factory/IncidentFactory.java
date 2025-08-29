package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Util.ValidationHelper;


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
    public static Incident build( String incidentType, String description) {
        ValidationHelper.requireNonBlank(incidentType, "incidentType");
        ValidationHelper.requireNonBlank(description, "description");

return new Incident.Builder()
        .incidentType(incidentType)
        .description(description)
        .build();
    }
}

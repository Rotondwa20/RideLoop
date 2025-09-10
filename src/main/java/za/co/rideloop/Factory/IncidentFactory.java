package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Domain.CustomerProfile;

public class IncidentFactory {

    // Simple factory method to create an Incident
    public static Incident createIncident(String type, String description, CustomerProfile profile) {
        return new Incident(type, description, profile);
    }
}

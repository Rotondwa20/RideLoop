package za.co.rideloop.Domain;
import java.util.Objects;

/**
 * RideLoop
 * Incident.java
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @date : 5/10/2025
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
public class Incident {
    private final int incidentID;
    private final String incidentType;
    private final String description;

    /**
     * Private constructor used by the Builder.
     * Can only be instantiated through the Builder.
     */
    private Incident(Builder builder) {
        this.incidentID = builder.incidentID;
        this.incidentType = builder.incidentType;
        this.description = builder.description;
    }

    // Getters
    public int getIncidentID() {
        return incidentID;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident = (Incident) o;
        return incidentID == incident.incidentID &&
                Objects.equals(incidentType, incident.incidentType) &&
                Objects.equals(description, incident.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidentID, incidentType, description);
    }

    @Override
    public String toString() {
        return "Incident{" +
                "incidentID=" + incidentID +
                ", incidentType='" + incidentType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Builder class for Incident.
     * Implements the Builder Pattern to create Incident instances in a fluent API style.
     */
    public static class Builder {
        private int incidentID;
        private String incidentType;
        private String description;

        public Builder incidentID(int incidentID) {
            this.incidentID = incidentID;
            return this;
        }

        public Builder incidentType(String incidentType) {
            this.incidentType = incidentType;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Incident build() {
            return new Incident(this);
        }
    }
}

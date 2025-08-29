package za.co.rideloop.Domain;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * RideLoop
 * Incident.java
 *
 * author : Swatsi Bongani Ratia
 * studnr : 230724477
 * group : 3I
 * date : 5/10/2025
 * Java version: "21.0.3" 2024-04-16 LTS
 */
@Entity
@Table(name = "incidents")   // ✅ table name should not conflict with SQL reserved keywords
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // ✅ auto-generate IDs
    private int incidentID;

   // @Column(nullable = false)   // ✅ enforce required fields
    private String incidentType;

  //  @Column(nullable = false)
    private String description;

    // Default constructor (required by JPA)
    protected Incident() {}

    // Private constructor for Builder
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

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Incident incident)) return false;
        return incidentID == incident.incidentID &&
                Objects.equals(incidentType, incident.incidentType) &&
                Objects.equals(description, incident.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidentID, incidentType, description);
    }

    // toString
    @Override
    public String toString() {
        return "Incident{" +
                "incidentID=" + incidentID +
                ", incidentType='" + incidentType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // Builder Pattern
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

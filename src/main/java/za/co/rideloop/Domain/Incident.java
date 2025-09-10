package za.co.rideloop.Domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incidentID;

    @Column(nullable = false)
    private String incidentType; // Security or Maintenance

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime incidentDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private CustomerProfile profile;

    // Default constructor for JPA
    protected Incident() {}

    // Simple constructor
    public Incident(String incidentType, String description, CustomerProfile profile) {
        this.incidentType = incidentType;
        this.description = description;
        this.profile = profile;
        this.incidentDate = LocalDateTime.now(); // Automatically set current time
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

    public LocalDateTime getIncidentDate() {
        return incidentDate;
    }

    public CustomerProfile getProfile() {
        return profile;
    }

    // Setters
    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfile(CustomerProfile profile) {
        this.profile = profile;
    }

    public void setIncidentDate(LocalDateTime incidentDate) {
        this.incidentDate = incidentDate;
    }
}

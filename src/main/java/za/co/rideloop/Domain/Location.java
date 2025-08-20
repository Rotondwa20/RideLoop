package za.co.rideloop.Domain;

/* Location.java
   Location POJO class with Builder pattern
   Author: Natasha Njili(221785345)
   Date: 11 May 2025
*/

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int locationID;
    private double longitude;
    private double latitude;

    // 🔹 Default constructor (required by JPA)
    protected Location() {}

    // 🔹 Private constructor for builder
    private Location(Builder builder) {
        this.locationID = builder.locationID;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
    }

    // 🔹 Getters
    public int getLocationID() {
        return locationID;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    // 🔹 Builder Class
    public static class Builder {
        private int locationID; // optional (usually generated)
        private double longitude;
        private double latitude;

        public Builder setLocationID(int locationID) {
            this.locationID = locationID;
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Location build() {
            return new Location(this);
        }

        public Builder copy(Location location) {
            this.locationID = location.locationID;
            this.longitude = location.longitude;
            this.latitude = location.latitude;
            return this;
        }

    }

    // 🔹 toString() for debugging
    @Override
    public String toString() {
        return "Location{" +
                "locationID=" + locationID +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}


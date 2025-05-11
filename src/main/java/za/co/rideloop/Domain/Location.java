package za.co.rideloop.Domain;

public class Location {
    private int locationID;
    private String streetName;
    private String suburb;
    private String province;
    private String zipCode;


    public Location() {
    }

    public int getLocationID() {
        return locationID;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getProvince() {
        return province;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationID=" + locationID +
                ", streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", province='" + province + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}

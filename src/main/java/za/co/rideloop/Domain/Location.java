package za.co.rideloop.Domain;
/* Location.java

     Location PoJO class

     Author: Natasha Njili(221785345)

     Date: 11 May 2025 */
public class Location {
    private int locationID;
    private String streetName;
    private String suburb;
    private String province;
    private String zipCode;


    private Location(Builder builder) {
        this.locationID = builder.locationID;
        this.streetName = builder.streetName;
        this.suburb = builder.suburb;
        this.province = builder.province;
        this.zipCode = builder.zipCode;
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
    public static class Builder {
        private int locationID;
        private String streetName;
        private String suburb;
        private String province;
        private String zipCode;

        public Builder setLocationID(int locationID) {
            this.locationID = locationID;
            return this;
        }

        public Builder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
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

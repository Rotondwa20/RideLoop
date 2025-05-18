package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Location;
/* LocationFactory.java

     Location Factory  class

     Author: Natasha Njili(221785345)

     Date: 18 May 2025 */
public class LocationFactory {

    public static Location createLocation(
            int locationID,
            String streetName,
            String suburb,
            String province,
            String zipCode
    ) {
        return new Location.Builder()
                .setLocationID(locationID)
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setProvince(province)
                .setZipCode(zipCode)
                .build();
    }
}


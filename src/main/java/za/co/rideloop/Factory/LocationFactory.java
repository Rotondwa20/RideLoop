package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Location;
/* LocationFactory.java

     Location Factory  class

     Author: Natasha Njili(221785345)

     Date: 18 May 2025 */
public class LocationFactory {

    // 🔹 Factory method for creating a Location
    public static Location createLocation(
            double longitude,
            double latitude
    ) {
        return new Location.Builder()
                .setLongitude(longitude)
                .setLatitude(latitude)
                .build();
    }
}



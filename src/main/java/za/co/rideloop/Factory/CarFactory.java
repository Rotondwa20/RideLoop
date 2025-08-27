package za.co.rideloop.Factory;

import jdk.javadoc.doclet.Taglet;
import za.co.rideloop.Domain.Car;
import za.co.rideloop.Domain.Location;

/* CarFactory.java

     CAR Factory class

     Author: Natasha Njili(221785345)

     Date: 18 May 2025 */
public class CarFactory {
    public static Car createCar(
            String brand,
            String model,
            int year,
            String licensePlate,
            double rentalRate,
            String status,
            String category,
            int mileage,
            String lastMaintenance,
            String maintenanceDue,
            Location location
    ) {
        return new Car.Builder()
                .setBrand(brand)
                .setModel(model)
                .setYear(year)
                .setLicensePlate(licensePlate)
                .setRentalRate(rentalRate)
                .setStatus(status)
                .setCategory(category)
                .setMileage(mileage)
                .setLastMaintenance(lastMaintenance)
                .setMaintenanceDue(maintenanceDue)
                .setLocation(location)
                .build();
    }

}

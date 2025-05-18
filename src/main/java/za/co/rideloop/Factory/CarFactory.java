package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Car;

public class CarFactory {
    public static Car createCar(
            int carId,
            String brand,
            String model,
            int year,
            String licensePlate,
            double rentalRate,
            String status,
            String category,
            int mileage,
            String lastMaintenance,
            String maintenanceDue
    ) {
        return new Car.Builder()
                .setCarId(carId)
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
                .build();
    }

}

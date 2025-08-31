package za.co.rideloop.Factory;


import za.co.rideloop.Domain.CarSupplier;
import za.co.rideloop.Util.ValidationHelper;

import java.util.Date;

/**
 * CarSupplierFactory.java
 * CarSupplierFactory model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */

public class CarSupplierFactory {
    public static CarSupplier build(String name, String contactPerson, Date supplyDate, String contractStatus) {
        ValidationHelper.requireNonBlank(name, "name");
        ValidationHelper.requireNonBlank(contactPerson, "contactPerson");
        ValidationHelper.requireNonNull(supplyDate, "supplyDate");
        ValidationHelper.requireNonNull(contractStatus, "contractStatus");

        return new CarSupplier.Builder()
                .name(name)
                .contactPerson(contactPerson)
                .supplyDate(supplyDate)
                .contractStatus(contractStatus)
                .build();
    }
}
//public class CarSupplierFactory {
//    public static CarSupplier build(String name, String contactPerson, Date supplyDate, String contractStatus) {
//        ValidationHelper.requireNonBlank(name, "name");
//        ValidationHelper.requireNonBlank(contactPerson, "contactPerson");
//        ValidationHelper.requireNonNull(supplyDate, "supplyDate");
//        ValidationHelper.requireNonNull(contractStatus, "contractStatus");
//
//        return new CarSupplier.Builder()
//                .name(name)
//                .contactPerson(contactPerson)
//                .supplyDate(supplyDate)
//                .contractStatus(contractStatus)
//                .build();
//    }
//}
//
//
//import za.co.rideloop.Domain.CarSupplier;
//import za.co.rideloop.Util.ValidationHelper;
//import java.util.Date;
//
////public class CarSupplierFactory {
////    public static CarSupplier build(String name, String contactPerson, Date supplyDate, String contractStatus) {
////        ValidationHelper.requireNonBlank(name, "name");
////        ValidationHelper.requireNonBlank(contactPerson, "contactPerson");
////        ValidationHelper.requireNonNull(supplyDate, "supplyDate");
////        ValidationHelper.requireNonNull(contractStatus, "contractStatus");
////
////        return new CarSupplier.Builder()
////                .name(name)
////                .contactPerson(contactPerson)
////                .supplyDate(supplyDate)
////                .contractStatus(contractStatus)
////                .build();
////    }
//////}//}
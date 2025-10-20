package za.co.rideloop.Util;

import java.time.LocalDate;

public class Helper {

    // ===== Common String Checks =====
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // ===== Numeric Checks =====
    public static boolean isValidInterger(Integer integer) {
        return integer != null && integer > 0;
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    // ===== Date Checks =====
    public static boolean isNotFutureDate(LocalDate date) {
        if (date == null) return false;
        return !date.isAfter(LocalDate.now());
    }

    public static boolean isValidDateRange(LocalDate start, LocalDate end) {
        if (start == null || end == null) return false;
        return !end.isBefore(start);
    }
    // ✅ Checks if a string is null or empty


    // ✅ Checks if an object is null
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    // ✅ Checks if two dates make sense (endDate after startDate)


    // ✅ Checks if a number is positive
    public static boolean isPositive(double number) {
        return number > 0;
    }

}

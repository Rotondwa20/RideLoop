package za.co.rideloop.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Pattern;

public class Helper {

    // ===== Common String Checks =====
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // ===== ID & Numeric Checks =====
    public static boolean isValidInterger(Integer interger) {
        return interger != null && interger > 0;
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isNotFutureDate(String paymentDate) {
        if (isNullOrEmpty(paymentDate)) return false;
        LocalDate date;
        try {
            date = LocalDate.parse(paymentDate);
        } catch (Exception e) {
            return false; // Invalid date format
        }
        return !date.isAfter(LocalDate.now(ZoneId.of("UTC")));
    }

    // ===== Date Checks =====
   /* public static boolean isValidDateRange(LocalDate start, LocalDate end) {
        if (start == null || end == null) return false;
        return !end.isBefore(start);

         public static boolean isNotFutureDate(LocalDate date) {
        if (date == null) return false;
        return !date.isAfter(LocalDate.now());

            public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(ZoneId.of("UTC"));

    // ===== Email Validation =====
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    }
    }
    }*/






}

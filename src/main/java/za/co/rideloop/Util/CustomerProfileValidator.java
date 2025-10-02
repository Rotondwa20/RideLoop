package za.co.rideloop.Util;

public class CustomerProfileValidator {

    // Check text fields (no null, no empty, no spaces only)
    public static void validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty or blank.");
        }
    }

    // Check number fields (must be numeric, no empty)
    public static void validateNumber(String value, String fieldName) {
        validateText(value, fieldName); // first check not empty
        if (!value.matches("\\d+")) { // only digits
            throw new IllegalArgumentException(fieldName + " must contain only numbers.");
        }
    }
}

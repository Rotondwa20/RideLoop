package za.co.rideloop.Util;

public class ValidationHelper {
    /**
     * Ensures the string is not null or blank.
     * @param value The string to validate.
     * @param fieldName The name of the field (for error messages).
     */
    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Invalid input - '" + fieldName + "' must not be empty");
        }
    }

    /**
     * Ensures the object is not null.
     * @param obj The object to validate.
     * @param fieldName The name of the field (for error messages).
     */
    public static void requireNonNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new IllegalArgumentException("Invalid input - '" + fieldName + "' must not be null");
        }
    }




}



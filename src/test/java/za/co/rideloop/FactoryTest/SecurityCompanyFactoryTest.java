package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Factory.SecurityCompanyFactory;


import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SecurityCompanyFactoryTest.java
 * SecurityCompanyFactoryTest model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */

class SecurityCompanyFactoryTest {
    private final Date startDate = new Date();
    private final Date endDate;

    public SecurityCompanyFactoryTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 6);  // Contract ends in 6 months
        endDate = calendar.getTime();
    }

    @Test
    void testBuildSuccess() {
        SecurityCompany company = SecurityCompanyFactory.build(
                101,
                "Shield Security Ltd.",
                "James Mokoena",
                "0712345678",
                "info@shieldsec.co.za",
                "Armed Response",
                startDate,
                endDate,
                4500.00,
                "0800-999-111",
                "Cape Town"
        );

        assertNotNull(company);
        assertEquals("Shield Security Ltd.", company.getName());
        assertEquals("James Mokoena", company.getContactPerson());
        assertEquals(4500.00, company.getMonthlyFee());
    }

    @Test
    void testBuildWithEmptyNameShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SecurityCompanyFactory.build(
                    102,
                    "",
                    "Jane Doe",
                    "0786543210",
                    "contact@abcsec.com",
                    "Surveillance",
                    startDate,
                    endDate,
                    3000.00,
                    "0800-555-000",
                    "Stellenbosch"
            );
        });

        assertTrue(exception.getMessage().contains("All fields"));
        System.err.println(exception.getMessage());
    }

    @Test
    void testBuildWithInvalidDateShouldThrowException() {
        Date invalidEndDate = new Date(startDate.getTime() - 86400000); // 1 day before start

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SecurityCompanyFactory.build(
                    103,
                    "Zeta Security",
                    "Leo Daniels",
                    "0799999999",
                    "support@zeta.co.za",
                    "Patrol",
                    startDate,
                    invalidEndDate,
                    5000.00,
                    "0800-123-456",
                    "Paarl"
            );
        });

        assertTrue(exception.getMessage().contains("Invalid contract dates"));
    }

    @Test
    void testBuildWithNegativeFeeShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SecurityCompanyFactory.build(
                    104,
                    "Delta Force",
                    "Ben Xulu",
                    "0722223333",
                    "help@delta.co.za",
                    "Monitoring",
                    startDate,
                    endDate,
                    -1000.00,
                    "0800-000-999",
                    "Somerset West"
            );
        });

        assertTrue(exception.getMessage().contains("Monthly fee"));
    }

}
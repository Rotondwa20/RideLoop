package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Factory.SecurityCompanyFactory;


import java.time.LocalDate;
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
    private final LocalDate  startDate = LocalDate.now();
    private final LocalDate endDate = LocalDate.now().plusDays(1);


    @Test
    void testBuildSuccess() {
        SecurityCompany company = SecurityCompanyFactory.build(

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
        System.out.println("Security Company Created: " + company);
        //System.out.println("security company created successfully"+ company.toString());
    }

    @Test
    void testBuildWithEmptyNameShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SecurityCompanyFactory.build(

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

        assertTrue(exception.getMessage().contains("Invalid input"));
        System.err.println(exception.getMessage());
    }


}
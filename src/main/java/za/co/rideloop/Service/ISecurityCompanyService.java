package za.co.rideloop.Service;

import za.co.rideloop.Domain.SecurityCompany;

import java.util.List;

/**
 * ISecurityCompany.java
 * ISecurityCompany model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
public interface ISecurityCompanyService extends IService<SecurityCompany, Integer> {
    SecurityCompany create(SecurityCompany securityCompany);

    List<SecurityCompany> getAll();
}

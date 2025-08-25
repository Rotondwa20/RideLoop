package za.co.rideloop.Service.Impl;

import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Repository.SecurityCompanyRepository;
import za.co.rideloop.Service.ISecurityCompanyService;

import java.util.List;

/**
 * SecurityCompanyServiceImpl.java
 * SecurityCompanyServiceImpl model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@Service
public class SecurityCompanyServiceImpl implements ISecurityCompanyService {
    private final SecurityCompanyRepository repository;

    public SecurityCompanyServiceImpl(SecurityCompanyRepository repository) {
        this.repository = repository;
    }

    @Override
   public SecurityCompany create(SecurityCompany securityCompany) {
        return repository.save(securityCompany);
    }

    @Override
    public SecurityCompany read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public SecurityCompany update(SecurityCompany securityCompany) {
        return repository.findById(securityCompany.getSecurityCompanyID())
                .map(existing -> {
                    SecurityCompany updated = new SecurityCompany.Builder()
                            .securityCompanyID(existing.getSecurityCompanyID())
                            .name(securityCompany.getName() != null ? securityCompany.getName() : existing.getName())
                            .contactPerson(securityCompany.getContactPerson() != null ? securityCompany.getContactPerson() : existing.getContactPerson())
                            .phone(securityCompany.getPhone() != null ? securityCompany.getPhone() : existing.getPhone())
                            .email(securityCompany.getEmail() != null ? securityCompany.getEmail() : existing.getEmail())
                            .serviceType(securityCompany.getServiceType() != null ? securityCompany.getServiceType() : existing.getServiceType())
                            .contractStartDate(securityCompany.getContractStartDate() != null ? securityCompany.getContractStartDate() : existing.getContractStartDate())
                            .contractEndDate(securityCompany.getContractEndDate() != null ? securityCompany.getContractEndDate() : existing.getContractEndDate())
                            .monthlyFee(securityCompany.getMonthlyFee() != 0 ? securityCompany.getMonthlyFee() : existing.getMonthlyFee())
                            .emergencyHotline(securityCompany.getEmergencyHotline() != null ? securityCompany.getEmergencyHotline() : existing.getEmergencyHotline())
                            .coverageArea(securityCompany.getCoverageArea() != null ? securityCompany.getCoverageArea() : existing.getCoverageArea())
                            .build();
                    return repository.save(updated);
                })
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<SecurityCompany> getAll() {
        return repository.findAll();
    }
}

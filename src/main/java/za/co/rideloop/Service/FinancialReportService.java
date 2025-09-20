/**
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/

package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.FinancialReport;
import za.co.rideloop.Repository.FinancialReportRepository;

import java.util.List;


@Service
public class FinancialReportService {

    @Autowired
    private FinancialReportRepository repository;

    public FinancialReport save(FinancialReport report) {
        return repository.save(report);
    }

    public FinancialReport read(int id) {
        return repository.findById(id).orElse(null);
    }

    public FinancialReport update(FinancialReport report) {
        return repository.save(report);
    }

    public boolean deleteById(int id) {
        repository.deleteById(id);
        return !repository.existsById(id);

    }

    public List<FinancialReport> getall() {
        return repository.findAll();
    }
}

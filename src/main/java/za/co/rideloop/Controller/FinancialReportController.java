/**
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/

package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.FinancialReport;
import za.co.rideloop.Service.FinancialReportService;

import java.util.List;

@RestController
@RequestMapping("/financialreport")

public class FinancialReportController {

    @Autowired
    private FinancialReportService financialReportService;

    @PostMapping("/save")
    public FinancialReport save(@RequestBody FinancialReport financialReport) {
        return financialReportService.save(financialReport);
    }

    @GetMapping("/read/{reportId}")
    public FinancialReport read(@PathVariable int reportId) {
        return financialReportService.read(reportId);
    }

    @PostMapping("/update")
    public FinancialReport update(@RequestBody FinancialReport financialReport) {
        return financialReportService.save(financialReport);
    }

    @DeleteMapping("/delete/{reportId}")
    public boolean delete(@PathVariable int reportId) {
        return financialReportService.deleteById(reportId);
    }

    @GetMapping("/getall")
    public List<FinancialReport> getAll() {
        return financialReportService.getall();
    }
}

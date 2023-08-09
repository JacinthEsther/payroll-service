package com.classwork.payroll.controller;

import com.classwork.payroll.dto.SalaryRateDto;
import com.classwork.payroll.model.Employee;
import com.classwork.payroll.model.SalaryRate;
import com.classwork.payroll.service.SalaryRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaryrates")
public class SalaryRateController {

    private final SalaryRateService salaryRateService;

    @Autowired
    public SalaryRateController(SalaryRateService salaryRateService) {
        this.salaryRateService = salaryRateService;
    }

    @PostMapping
    public SalaryRate createSalaryRate(@RequestBody SalaryRateDto salaryRateDto) {
        return salaryRateService.createSalaryRate(salaryRateDto);
    }

    @PutMapping("/{salaryRateId}")
    public SalaryRate updateSalaryRate(@PathVariable int salaryRateId, @RequestBody SalaryRateDto salaryRateDto) {
        return salaryRateService.updateSalaryRate(salaryRateDto, salaryRateId);
    }

    @GetMapping("/{salaryRateId}")
    public SalaryRate getSalaryRateById(@PathVariable int salaryRateId) {
        return salaryRateService.findSalaryRateById(salaryRateId);
    }

    @GetMapping
    public List<SalaryRate> getAllSalaryRates() {
        return salaryRateService.getSalaryRateList();
    }

    @GetMapping("{salaryRateId}/employees")
    public List<Employee> getAllEmployeesForSalaryRate(@PathVariable int salaryRateId) {
        return salaryRateService.getAllEmployeesForSalaryRate(salaryRateId);
    }
}


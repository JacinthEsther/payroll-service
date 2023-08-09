package com.classwork.payroll.service;

import com.classwork.payroll.dto.SalaryRateDto;
import com.classwork.payroll.exception.PayrollException;
import com.classwork.payroll.model.Employee;
import com.classwork.payroll.model.EmployeeCategory;
import com.classwork.payroll.model.SalaryRate;
import com.classwork.payroll.repository.SalaryRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryRateService {
    @Autowired
    private SalaryRateRepository salaryRateRepository;
    @Autowired
    private EmployeeCategoryService employeeCategoryService;
    @Autowired
    private EmployeeService employeeService;

    public SalaryRate createSalaryRate(SalaryRateDto salaryRateDto){

        EmployeeCategory employeeCategoryById = employeeCategoryService.findEmployeeCategoryById(salaryRateDto.getEmployeeCategoryId());
        SalaryRate salaryRate = new SalaryRate();
        salaryRate.setAmount(salaryRateDto.getAmount());
        salaryRate.setEmployeeLevel(salaryRateDto.getEmployeeLevel());
        salaryRate.setEmployeeCategory(employeeCategoryById);

        return salaryRateRepository.create(salaryRate);
    }


    public SalaryRate updateSalaryRate(SalaryRateDto salaryRateDto, int id){
        SalaryRate salaryRate=salaryRateRepository.findById(id).orElseThrow(
                ()-> new PayrollException("Not found")
        );

        if(salaryRateDto.getAmount()!= null){
            salaryRate.setAmount(salaryRateDto.getAmount());
        }

        if(salaryRateDto.getEmployeeLevel()!= null){
            salaryRate.setEmployeeLevel(salaryRateDto.getEmployeeLevel());
        }

        if(salaryRateDto.getEmployeeCategoryId()!= null){
            EmployeeCategory employeeCategoryById = employeeCategoryService.findEmployeeCategoryById(salaryRateDto.getEmployeeCategoryId());
            salaryRate.setEmployeeCategory(employeeCategoryById);
        }
        return salaryRateRepository.update(salaryRate);
    }

    public SalaryRate findSalaryRateById(int id){
       return salaryRateRepository.findById(id).orElseThrow(
                ()-> new PayrollException("Not found")
        );
    }

    public List<SalaryRate> getSalaryRateList(){
        return salaryRateRepository.findAll();
    }

    public List<Employee> getAllEmployeesForSalaryRate(int salaryRateId) {
        SalaryRate salaryRate = salaryRateRepository.findById(salaryRateId).orElseThrow(
                () -> new PayrollException("not found")
        );
        return employeeService.findAllByEmployeeCategoryAndEmployeeLevel(salaryRate.getEmployeeCategory(),
                salaryRate.getEmployeeLevel());
    }
}

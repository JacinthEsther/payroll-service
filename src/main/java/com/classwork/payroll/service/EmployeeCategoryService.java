package com.classwork.payroll.service;

import com.classwork.payroll.dto.EmployeeCategoryDto;
import com.classwork.payroll.exception.PayrollException;
import com.classwork.payroll.model.Employee;
import com.classwork.payroll.model.EmployeeCategory;
import com.classwork.payroll.repository.EmployeeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeCategoryService {
    @Autowired
    private EmployeeCategoryRepository employeeCategoryRepository;

    public EmployeeCategory createEmployeeCategory(EmployeeCategoryDto createEmployeeCategory) {

        Optional<EmployeeCategory> foundEmployeeCategory = employeeCategoryRepository.findByName(createEmployeeCategory.getName());
        if(foundEmployeeCategory.isPresent()){
            throw new PayrollException("Employee Category already exist");
        }
        EmployeeCategory employeeCategory = new EmployeeCategory();
        employeeCategory.setName(createEmployeeCategory.getName());
        employeeCategory.setDescription(createEmployeeCategory.getDescription());
       return employeeCategoryRepository.create(employeeCategory);
    }


    public EmployeeCategory updateEmployeeCategory(EmployeeCategoryDto employeeCategoryDto, int id) {
        EmployeeCategory employeeCategory = employeeCategoryRepository.findById(id).orElseThrow(
                ()-> new PayrollException("Employee Category does not exist")
        );

        if(employeeCategoryDto.getName()!= null) {
            employeeCategory.setName(employeeCategoryDto.getName());
        }
        if(employeeCategoryDto.getDescription()!= null) {
            employeeCategory.setDescription(employeeCategoryDto.getDescription());
        }

        return employeeCategoryRepository.update(employeeCategory);
    }

    public EmployeeCategory findEmployeeCategoryById(int id) {
        return employeeCategoryRepository.findById(id).orElseThrow(
                ()-> new PayrollException("Employee Category does not exist")
        );
    }

    public List<EmployeeCategory> getAllEmployeeCategory() {
        return employeeCategoryRepository.findAll();
    }
}

package com.classwork.payroll.controller;


import com.classwork.payroll.dto.employeeDto.CreateEmployeeDto;
import com.classwork.payroll.dto.employeeDto.UpdateEmployeeDto;
import com.classwork.payroll.model.Employee;
import com.classwork.payroll.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployeeDetails( @RequestBody CreateEmployeeDto createEmployeeDto) throws ParseException {
        Employee savedEmployee = employeeService.saveEmployeeDetails(createEmployeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId,  @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        Employee updatedEmployee = employeeService.updateCustomer(updateEmployeeDto, employeeId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}/employees")
    public List<Employee> getAllEmployeesManagedByManager(@PathVariable int managerId) {
        return employeeService.getAllEmployeesManagedByManager(managerId);
    }
}


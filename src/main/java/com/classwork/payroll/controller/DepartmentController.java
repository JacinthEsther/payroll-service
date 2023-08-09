package com.classwork.payroll.controller;

import com.classwork.payroll.dto.DepartmentDto.DepartmentDto;
import com.classwork.payroll.model.Department;
import com.classwork.payroll.model.Employee;
import com.classwork.payroll.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department createDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.createDepartment(departmentDto);
    }

    @PutMapping("/{departmentId}")
    public Department updateDepartment(@PathVariable int departmentId, @RequestBody DepartmentDto departmentDto) {
        return departmentService.updateDepartment(departmentDto, departmentId);
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable int departmentId) {
        return departmentService.getADepartmentId(departmentId);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
    @GetMapping("{departmentId}/employees")
    public List<Employee> getAllEmployeesForDepartment(@PathVariable int departmentId) {
        return departmentService.getAllEmployeesForDepartment(departmentId);
    }
}


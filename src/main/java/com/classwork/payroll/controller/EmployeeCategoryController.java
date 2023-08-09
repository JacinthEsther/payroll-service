package com.classwork.payroll.controller;

import com.classwork.payroll.dto.EmployeeCategoryDto;
import com.classwork.payroll.model.EmployeeCategory;
import com.classwork.payroll.service.EmployeeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employeecategories")
public class EmployeeCategoryController {

    private final EmployeeCategoryService employeeCategoryService;

    @Autowired
    public EmployeeCategoryController(EmployeeCategoryService employeeCategoryService) {
        this.employeeCategoryService = employeeCategoryService;
    }

    @PostMapping
    public EmployeeCategory createEmployeeCategory(@RequestBody EmployeeCategoryDto employeeCategoryDto) {
        return employeeCategoryService.createEmployeeCategory(employeeCategoryDto);
    }

    @PutMapping("/{employeeCategoryId}")
    public EmployeeCategory updateEmployeeCategory(@PathVariable int employeeCategoryId, @RequestBody EmployeeCategoryDto employeeCategoryDto) {
        return employeeCategoryService.updateEmployeeCategory(employeeCategoryDto, employeeCategoryId);
    }

    @GetMapping("/{employeeCategoryId}")
    public EmployeeCategory getEmployeeCategoryById(@PathVariable int employeeCategoryId) {
        return employeeCategoryService.findEmployeeCategoryById(employeeCategoryId);
    }

    @GetMapping
    public List<EmployeeCategory> getAllEmployeeCategories() {
        return employeeCategoryService.getAllEmployeeCategory();
    }
}


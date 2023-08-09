package com.classwork.payroll.service;

import com.classwork.payroll.dto.EmployeeCategoryDto;
import com.classwork.payroll.dto.employeeDto.UpdateEmployeeDto;
import com.classwork.payroll.exception.PayrollException;
import com.classwork.payroll.model.Employee;
import com.classwork.payroll.model.EmployeeCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeCategoryServiceTest {

    @Autowired
    private EmployeeCategoryService employeeCategoryService;

    @Test
    public void testThatCanCreateAnEmployeeCategory(){
        EmployeeCategoryDto createEmployeeCategory = EmployeeCategoryDto.builder()
                .name("full-time")
                .description("full time staff")
                .build();

        EmployeeCategory employeeCategory = employeeCategoryService.createEmployeeCategory(createEmployeeCategory);
        assertEquals(1, employeeCategory.getId());
    }

    @Test
    public void testThatAnEmployeeCategoryCannotBeCreatedMoreThanOnce(){
        EmployeeCategoryDto createEmployeeCategory = EmployeeCategoryDto.builder()
                .name("full-time")
                .description("full time staff")
                .build();
        Assertions.assertThrows(PayrollException.class,()->employeeCategoryService.createEmployeeCategory(createEmployeeCategory)) ;

    }


    @Test
    public void testThatCanUpdateEmployeeCategory(){
        EmployeeCategoryDto employeeCategoryDto = EmployeeCategoryDto.builder()
                .name("Full_Time")
                .description("Full_Time_Staff")
                .build();
        int id = 1;
        EmployeeCategory em=employeeCategoryService.updateEmployeeCategory(employeeCategoryDto,id);
        assertEquals(em.getName(),"Full_Time");
    }

    @Test
    public void testThatCanGetAnEmployeeCategoryById(){
        int id = 1;
        EmployeeCategory em =employeeCategoryService.findEmployeeCategoryById(id);
        assertNotNull(em);
    }

    @Test
    public void testThatCanGetAllEmployeeCategories(){
        List<EmployeeCategory> allEmployeeCategories = employeeCategoryService.getAllEmployeeCategory();

        assertEquals(1, allEmployeeCategories.size());
    }

}
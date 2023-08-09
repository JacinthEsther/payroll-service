package com.classwork.payroll.service;

import com.classwork.payroll.dto.employeeDto.CreateEmployeeDto;
import com.classwork.payroll.dto.employeeDto.UpdateEmployeeDto;
import com.classwork.payroll.exception.PayrollException;
import com.classwork.payroll.model.Employee;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testThatCanCreateEmployee() throws ParseException {
        CreateEmployeeDto createEmployeeDto = CreateEmployeeDto.builder()
                .name("Esther")
                .phone("09095861220")
                .address("H4, David's Court, Van Daniel's Estate")
                .dateOfBirth("13/04/2019")
                .joinedDate("13/04/2023")
                .build();
        Employee employee = employeeService.saveEmployeeDetails(createEmployeeDto);
        assertEquals(employee.getId(), 1);
        assertNull(employee.getEmployeeCategory());
    }

    @Test
    public void testThatAnEmployeeCanBeRegisteredOnce(){
        CreateEmployeeDto createEmployeeDto = CreateEmployeeDto.builder()
                .name("Esther")
                .phone("09095861220")
                .address("H4, David's Court, Van Daniel's Estate")
                .dateOfBirth("13/04/2019")
                .joinedDate("13/04/2023")
                .build();
        Assertions.assertThrows(PayrollException.class,()-> employeeService.saveEmployeeDetails(createEmployeeDto));
    }

    @Test
    public void testThatCanUpdateEmployee(){
        UpdateEmployeeDto updateEmployeeDto = UpdateEmployeeDto.builder()
                .address("Ikija Street, Shomolu")
                .name("Miss Esther Jacinta")
                .build();
        int id = 1;
        Employee em=employeeService.updateCustomer(updateEmployeeDto,id);
        assertEquals(em.getName(),"Miss Esther Jacinta");
    }

    @Test
    public void testThatCanGetAnEmployeeById(){
        int id = 1;
        Employee em =employeeService.findEmployeeById(id);
        assertNotNull(em);
    }

    @Test
    public void testThatCanGetAllEmployee(){
        List<Employee> allEmployees = employeeService.getAllEmployees();

        assertEquals(1, allEmployees.size());
    }

}
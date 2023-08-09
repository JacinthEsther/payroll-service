package com.classwork.payroll.service;

import com.classwork.payroll.dto.DepartmentDto.DepartmentDto;
import com.classwork.payroll.exception.PayrollException;
import com.classwork.payroll.model.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testThatCanCreateADepartment(){
        DepartmentDto departmentDto = DepartmentDto.builder()
                .name("Technology")
                .description("Technology and Operations Department")
                .managerId(1)
                .build();

        Department department = departmentService.createDepartment(departmentDto);
        assertEquals(1,department.getId());

    }

    @Test
    public void testThatADepartmentCanBeRegisteredOnce() {
        DepartmentDto departmentDto = DepartmentDto.builder()
                .name("Technology")
                .description("Technology and Operations Department")
                .managerId(1)
                .build();

        Assertions.assertThrows(PayrollException.class,()-> departmentService.createDepartment(departmentDto));

    }
    @Test
    public void testThatCanUpdateADepartment(){
        DepartmentDto departmentDto = DepartmentDto.builder()
                .name("Technology and Operations")
                .description("Technology,Operations and Development Department")
                .managerId(1)
                .build();
        int departmentId = 1;
        departmentService.updateDepartment(departmentDto, departmentId);
    }

    @Test
    public void testThatCanRetrieveADepartment(){
        int departmentId =1;
        Department department = departmentService.getADepartmentId(departmentId);
        assertNotNull(department);
    }

    @Test
    public void testThatCanGetAllEmployee(){
        List<Department> allDepartments = departmentService.getAllDepartments();

        assertEquals(1, allDepartments.size());
    }
}

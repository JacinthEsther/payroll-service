package com.classwork.payroll.service;

import com.classwork.payroll.dto.DepartmentDto.DepartmentDto;
import com.classwork.payroll.exception.PayrollException;
import com.classwork.payroll.model.Department;
import com.classwork.payroll.model.Employee;
import com.classwork.payroll.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(DepartmentDto departmentDto) {

        Optional<Department> foundDepartment = departmentRepository.findByName(departmentDto.getName());

        if(foundDepartment.isPresent()){
            throw new PayrollException("Department already exist");
        }

        employeeService.findEmployeeById(departmentDto.getManagerId());

        Department department = new Department();
        department.setDescription(departmentDto.getDescription());
        department.setName(departmentDto.getName());
        department.setManagerId(departmentDto.getManagerId());

        return departmentRepository.create(department);
    }

    public Department updateDepartment(DepartmentDto departmentDto, int departmentId) {

        Department department=departmentRepository.findById(departmentId).orElseThrow(
                ()->new PayrollException("Department not found")
        );

        Integer managerId = departmentDto.getManagerId();
        if(managerId!= null){
        employeeService.findEmployeeById(departmentDto.getManagerId());
            department.setManagerId(departmentDto.getManagerId());
        }
        if(departmentDto.getDescription()!=null){
            department.setDescription(departmentDto.getDescription());
        }

        if(departmentDto.getName()!=null){
            department.setName(departmentDto.getName());
        }
       return departmentRepository.update(department);
    }

    public Department getADepartmentId(int departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(
                ()->new PayrollException("Department does not exist")
        );
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Employee> getAllEmployeesForDepartment(int departmentId) {
       departmentRepository.findById(departmentId).orElseThrow(
                () -> new PayrollException("not found")
        );
       return employeeService.getAllEmployeesForDepartment(departmentId);
    }
}

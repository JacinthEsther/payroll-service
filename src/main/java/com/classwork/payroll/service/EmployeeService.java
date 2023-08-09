package com.classwork.payroll.service;

import com.classwork.payroll.dto.employeeDto.CreateEmployeeDto;
import com.classwork.payroll.dto.employeeDto.UpdateEmployeeDto;
import com.classwork.payroll.exception.PayrollException;
import com.classwork.payroll.model.Department;
import com.classwork.payroll.model.Employee;
import com.classwork.payroll.model.EmployeeCategory;
import com.classwork.payroll.model.SalaryRate;
import com.classwork.payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeCategoryService employeeCategoryService;
    @Autowired
    private SalaryRateService salaryRateService;

    public Employee saveEmployeeDetails(CreateEmployeeDto createEmployeeDto) throws ParseException {

        Optional<Employee> foundEmployee = employeeRepository.findByPhoneNumber(createEmployeeDto.getPhone());
        if(foundEmployee.isPresent()){
            throw new PayrollException("employee already exist");
        }

            Employee employee = new Employee();
            employee.setName(createEmployeeDto.getName());
            employee.setPhone(createEmployeeDto.getPhone());
            employee.setAddress(createEmployeeDto.getAddress());

            Date joinedDate = parseStringDate(createEmployeeDto.getJoinedDate());
            Date dateOfBirth = parseStringDate(createEmployeeDto.getDateOfBirth());
            employee.setDateOfBirth(dateOfBirth);
            employee.setJoinedDate(joinedDate);

            return employeeRepository.create(employee);

    }

    private Date parseStringDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       return sdf.parse(date);
    }

    public Employee updateCustomer(UpdateEmployeeDto updateEmployeeDto, int id) {

        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(
                ()-> new PayrollException("Employee does not exist")
        );

        if(updateEmployeeDto.getName()!= null) {
            foundEmployee.setName(updateEmployeeDto.getName());
        }
        if(updateEmployeeDto.getAddress()!= null) {
            foundEmployee.setAddress(updateEmployeeDto.getAddress());
        }
        if(updateEmployeeDto.getDepartmentId()!= null){
            Department department= departmentService.getADepartmentId(updateEmployeeDto.getDepartmentId());
            foundEmployee.setEmployeeDepartment(department);
        }
        if(updateEmployeeDto.getEmployeeCategoryId()!=null){
            EmployeeCategory employeeCategory= employeeCategoryService.findEmployeeCategoryById(updateEmployeeDto.getEmployeeCategoryId());
            foundEmployee.setEmployeeCategory(employeeCategory);
        }

       return employeeRepository.update(foundEmployee);

    }

    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(
                ()-> new PayrollException("Employee not found")
        );
    }

    public List<Employee> getAllEmployees() {
       return employeeRepository.findAll();
    }


    public List<Employee> findAllByEmployeeCategoryAndEmployeeLevel(EmployeeCategory employeeCategory, int employeeLevel) {
        return employeeRepository.findAllByEmployeeCategoryAndEmployeeLevel(employeeCategory,employeeLevel);
    }

    public List<Employee> getAllEmployeesForDepartment(int departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }

    public List<Employee> getAllEmployeesManagedByManager(int managerId) {
        return employeeRepository.findAllManagedByManager(managerId);
    }
}

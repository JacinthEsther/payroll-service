package com.classwork.payroll.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String address;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "joined_date")
    private Date joinedDate;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department employeeDepartment;
    @ManyToOne
    @JoinColumn(name = "employee_category_id")
    private EmployeeCategory employeeCategory;
}

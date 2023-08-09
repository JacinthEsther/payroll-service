package com.classwork.payroll.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salary-rate")
@ToString
@Entity
public class SalaryRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "employee_category_id")
    private EmployeeCategory employeeCategory;
    @Column(name = "employee_level")
    private int employeeLevel;
    private BigDecimal amount;
}

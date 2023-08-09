package com.classwork.payroll.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.relational.core.sql.In;

import java.math.BigDecimal;

@Builder
@Getter
public class SalaryRateDto {
    private Integer employeeLevel;
    private BigDecimal amount;
    private Integer EmployeeCategoryId;
}

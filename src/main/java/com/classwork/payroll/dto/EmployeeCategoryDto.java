package com.classwork.payroll.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeCategoryDto {
    private String name;
    private String description;
}

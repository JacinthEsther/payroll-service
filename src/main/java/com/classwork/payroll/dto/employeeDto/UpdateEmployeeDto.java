package com.classwork.payroll.dto.employeeDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateEmployeeDto {

    private String name;
    private String address;
    private Integer employeeCategoryId;
    private Integer departmentId;

}

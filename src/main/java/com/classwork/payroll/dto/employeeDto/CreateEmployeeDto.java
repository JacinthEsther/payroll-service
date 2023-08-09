package com.classwork.payroll.dto.employeeDto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class CreateEmployeeDto {
    private String name;
    private String phone;
    private String address;
    private String dateOfBirth;
    private String joinedDate;
}

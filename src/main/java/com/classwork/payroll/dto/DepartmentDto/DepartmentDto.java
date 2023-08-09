package com.classwork.payroll.dto.DepartmentDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DepartmentDto {
    private String name;
    private String description;
    private Integer managerId;
}

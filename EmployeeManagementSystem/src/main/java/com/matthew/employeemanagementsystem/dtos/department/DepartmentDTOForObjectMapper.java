package com.matthew.employeemanagementsystem.dtos.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Immutable
public class DepartmentDTOForObjectMapper {
    private String departmentName;
}

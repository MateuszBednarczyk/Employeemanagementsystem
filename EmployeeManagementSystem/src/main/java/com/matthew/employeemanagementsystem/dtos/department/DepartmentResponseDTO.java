package com.matthew.employeemanagementsystem.dtos.department;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.List;

@NoArgsConstructor
@Data
@Immutable
public class DepartmentResponseDTO {
    private String departmentName;
    private List<EmployeeEntity> employeesList;

    public DepartmentResponseDTO(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentResponseDTO(String departmentName, List<EmployeeEntity> employeesList) {
        this.departmentName = departmentName;
        this.employeesList = employeesList;
    }
}

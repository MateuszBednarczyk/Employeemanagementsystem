package com.matthew.employeemanagementsystem.dtos.employee;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Immutable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeResponseDTO {
    private String name;
    private String surname;
    private List<DepartmentEntity> departmentList;
}

package com.matthew.employeemanagementsystem.mapper;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeModelMapper {
    EmployeeResponseDTO mapEmployeeEntityToEmployeeResponseDTO(EmployeeEntity employeeEntity);
}

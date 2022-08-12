package com.matthew.employeemanagementsystem.mapper;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EmployeeModelMapper {
    EmployeeResponseDTO mapEmployeeEntityToEmployeeResponseDTO(EmployeeEntity employeeEntity);
}

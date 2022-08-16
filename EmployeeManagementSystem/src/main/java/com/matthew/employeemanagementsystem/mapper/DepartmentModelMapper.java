package com.matthew.employeemanagementsystem.mapper;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentModelMapper {
    DepartmentResponseDTO mapDepartmentEntityToDepartmentResponseDTO(DepartmentEntity departmentEntity);
}

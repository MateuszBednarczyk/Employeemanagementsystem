package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;

interface DepartmentFindingService {
    DepartmentEntity getDepartmentEntity(String department);
    DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName);
}

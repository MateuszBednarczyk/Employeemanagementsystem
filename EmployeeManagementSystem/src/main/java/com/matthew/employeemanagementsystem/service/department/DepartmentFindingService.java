package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentDTOForObjectMapper;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

interface DepartmentFindingService {
    DepartmentEntity getDepartmentEntity(String department);

    DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName);

    List<DepartmentDTOForObjectMapper> findAllDepartments(Principal loggedUser) throws AccessDeniedException;
}

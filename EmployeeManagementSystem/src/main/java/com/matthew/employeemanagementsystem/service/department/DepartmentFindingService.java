package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNoPermissionException;

import java.security.Principal;
import java.util.List;

interface DepartmentFindingService {
    DepartmentEntity getDepartmentEntity(String department);

    DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName);

    List<DepartmentResponseDTO> findAllDepartments(Principal loggedUser) throws DepartmentNoPermissionException;
}

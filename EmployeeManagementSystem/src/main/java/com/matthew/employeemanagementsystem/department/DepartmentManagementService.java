package com.matthew.employeemanagementsystem.department;

import com.matthew.employeemanagementsystem.department.dtos.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.types.DepartmentType;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

public interface DepartmentManagementService {

    @Transactional
    ResponseEntity<String> addNewDepartment(AddNewDepartmentRequestDTO requestDTO);

    DepartmentEntity getDepartmentEntity(DepartmentType departmentType);
}

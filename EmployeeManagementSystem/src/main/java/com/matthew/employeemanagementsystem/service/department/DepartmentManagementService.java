package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

public interface DepartmentManagementService {

    @Transactional
    ResponseEntity<String> addNewDepartment(AddNewDepartmentRequestDTO requestDTO);

    DepartmentEntity getDepartmentEntity(String department);
}

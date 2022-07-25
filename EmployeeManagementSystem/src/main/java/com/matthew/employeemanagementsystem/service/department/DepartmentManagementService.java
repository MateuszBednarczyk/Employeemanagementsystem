package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;

import javax.transaction.Transactional;

interface DepartmentManagementService {

    @Transactional
    DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO);


}

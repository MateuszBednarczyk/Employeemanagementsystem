package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;

import java.util.List;

interface EmployeeFindingService {
    EmployeeEntity findEmployeeEntityByNameAndSurname(String name, String surname);

    EmployeeResponseDTO findEmployeeByNameAndSurnameAndReturnItAsDTO(String name, String surname);

    List<EmployeeResponseDTO> findEmployeesInDepartment(String departmentName);

    EmployeeEntity findById(Long id);
}

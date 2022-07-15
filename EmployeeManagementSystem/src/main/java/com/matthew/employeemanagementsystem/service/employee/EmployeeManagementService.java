package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

public interface EmployeeManagementService {
    @Transactional
    ResponseEntity<String> addNewEmployee(AddNewEmployeeRequestDTO requestDTO);
    ResponseEntity<EmployeeEntity> findEmployeeByNameAndSurname(String name, String surname);
}

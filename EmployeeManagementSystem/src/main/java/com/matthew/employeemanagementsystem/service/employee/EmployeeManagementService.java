package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.Optional;

public interface EmployeeManagementService {
    @Transactional
    ResponseEntity<String> addNewEmployee(AddNewEmployeeRequestDTO requestDTO);
    ResponseEntity<Optional<EmployeeEntity>> findEmployeeByNameAndSurname(String name, String surname);
}

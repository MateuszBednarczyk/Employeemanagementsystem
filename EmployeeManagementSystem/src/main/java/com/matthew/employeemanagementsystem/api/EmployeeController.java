package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.service.employee.EmployeeManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
class EmployeeController {

    private final EmployeeManagementService employeeManagementService;

    @PostMapping("/api/employees/add")
    public ResponseEntity<String> addNewEmployee(@RequestBody AddNewEmployeeRequestDTO requestDTO) {

        return employeeManagementService.addNewEmployee(requestDTO);
    }


    @GetMapping("/api/employees")
    public ResponseEntity<EmployeeEntity> findEmployeeByNameAndSurname(@RequestParam String name, @RequestParam String surname) {

        return employeeManagementService.findEmployeeByNameAndSurname(name, surname);
    }
}
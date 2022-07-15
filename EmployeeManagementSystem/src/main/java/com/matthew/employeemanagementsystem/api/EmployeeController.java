package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.service.employee.EmployeeManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class EmployeeController {

    private final EmployeeManagementService employeeManagementService;

    @PostMapping("/api/employees/add")
    public ResponseEntity<String> addNewEmployee(@RequestBody AddNewEmployeeRequestDTO requestDTO) {

        return employeeManagementService.addNewEmployee(requestDTO);
    }

}
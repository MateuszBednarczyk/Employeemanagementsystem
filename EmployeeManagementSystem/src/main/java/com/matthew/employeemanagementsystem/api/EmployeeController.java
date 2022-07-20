package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.service.employee.EmployeeManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class EmployeeController {

    private final EmployeeManagementService employeeManagementService;

    @PostMapping("/api/employees/add")
    public ResponseEntity<EmployeeResponseDTO> addNewEmployee(@RequestBody AddNewEmployeeRequestDTO requestDTO) {
        return new ResponseEntity<>(employeeManagementService.checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(requestDTO), HttpStatus.OK);
    }


    @GetMapping("/api/employees")
    public ResponseEntity<EmployeeResponseDTO> findEmployeeByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return new ResponseEntity<>(employeeManagementService.findEmployeeByNameAndSurname(name, surname), HttpStatus.OK);
    }

    @DeleteMapping("/api/employees/delete")
    public ResponseEntity<String> deleteEmployeeByNameAndSurname(@RequestBody DeleteEmployeeRequestDTO requestDTO) {
        employeeManagementService.deleteEmployeeByNameAndSurname(requestDTO);

        return new ResponseEntity<>(HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED);
    }
}
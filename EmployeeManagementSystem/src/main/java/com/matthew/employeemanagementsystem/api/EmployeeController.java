package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.service.employee.EmployeeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees/")
class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @PostMapping("add")
    public ResponseEntity<EmployeeResponseDTO> addNewEmployee(@RequestBody AddNewEmployeeRequestDTO requestDTO) {
        return new ResponseEntity<>(employeeFacade.checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(requestDTO), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<EmployeeResponseDTO> findEmployeeByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return new ResponseEntity<>(employeeFacade.findEmployeeByNameAndSurname(name, surname), HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteEmployeeByNameAndSurname(@RequestBody DeleteEmployeeRequestDTO requestDTO) {
        employeeFacade.deleteEmployeeByNameAndSurname(requestDTO);

        return new ResponseEntity<>(HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED);
    }

    @GetMapping("{departmentName}")
    public ResponseEntity<List<EmployeeResponseDTO>> findEmployeesInDepartment(@PathVariable String departmentName) {
        return new ResponseEntity<>(employeeFacade.findEmployeesInDepartment(departmentName), HttpStatus.OK);
    }
}
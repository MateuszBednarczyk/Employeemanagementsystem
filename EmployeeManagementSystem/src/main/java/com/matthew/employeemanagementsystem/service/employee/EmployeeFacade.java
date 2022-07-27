package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;

import javax.transaction.Transactional;

public interface EmployeeFacade {
    EmployeeResponseDTO findEmployeeByNameAndSurname(String name, String surname);

    @Transactional
    EmployeeResponseDTO checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(AddNewEmployeeRequestDTO requestDTO);

    void deleteEmployeeByNameAndSurname(DeleteEmployeeRequestDTO requestDTO);
}
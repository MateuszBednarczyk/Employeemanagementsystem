package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;

import javax.transaction.Transactional;

public interface EmployeeManagementService {
    @Transactional
    EmployeeResponseDTO checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(AddNewEmployeeRequestDTO requestDTO);

    EmployeeResponseDTO findEmployeeByNameAndSurname(String name, String surname);

    void deleteEmployeeByNameAndSurname(DeleteEmployeeRequestDTO requestDTO);
}

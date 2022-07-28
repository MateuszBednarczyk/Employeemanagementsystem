package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;

import javax.transaction.Transactional;

interface EmployeeManagementService {
    @Transactional
    EmployeeResponseDTO checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(AddNewEmployeeRequestDTO requestDTO);

    @Transactional
    void deleteEmployeeByNameAndSurname(DeleteEmployeeRequestDTO requestDTO);
}

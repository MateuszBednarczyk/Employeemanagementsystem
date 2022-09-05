package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.dtos.employee.ModifyEmployeeRequestDTO;

interface EmployeeManagementService {

    EmployeeResponseDTO checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(AddNewEmployeeRequestDTO requestDTO);

    void deleteEmployeeByNameAndSurname(DeleteEmployeeRequestDTO requestDTO);

    void modifyEmployee(ModifyEmployeeRequestDTO requestDTO);

}

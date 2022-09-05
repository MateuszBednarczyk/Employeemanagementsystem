package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.dtos.employee.ModifyEmployeeRequestDTO;

import java.util.List;

public interface EmployeeFacade {
    EmployeeResponseDTO findEmployeeByNameAndSurname(String name, String surname);

    EmployeeResponseDTO checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(AddNewEmployeeRequestDTO requestDTO);

    void deleteEmployeeByNameAndSurname(DeleteEmployeeRequestDTO requestDTO);

    List<EmployeeResponseDTO> findEmployeesInDepartment(String departmentName);

    void modifyEmployee(ModifyEmployeeRequestDTO requestDTO);
}

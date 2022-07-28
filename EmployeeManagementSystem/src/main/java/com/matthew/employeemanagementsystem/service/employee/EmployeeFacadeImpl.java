package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeFindingService employeeFindingService;
    private final EmployeeManagementService employeeManagementService;

    @Override
    public EmployeeResponseDTO findEmployeeByNameAndSurname(String name, String surname) {
        return employeeFindingService.findEmployeeByNameAndSurnameAndReturnItAsDTO(name, surname);
    }

    @Override
    public EmployeeResponseDTO checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(AddNewEmployeeRequestDTO requestDTO) {
        return employeeManagementService.checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(requestDTO);
    }

    @Override
    public void deleteEmployeeByNameAndSurname(DeleteEmployeeRequestDTO requestDTO) {
        employeeManagementService.deleteEmployeeByNameAndSurname(requestDTO);
    }
}

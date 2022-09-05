package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.dtos.employee.ModifyEmployeeRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<EmployeeResponseDTO> findEmployeesInDepartment(String departmentName) {
        return employeeFindingService.findEmployeesInDepartment(departmentName);
    }

    @Override
    public void modifyEmployee(ModifyEmployeeRequestDTO requestDTO) {
        employeeManagementService.modifyEmployee(requestDTO);
    }
}

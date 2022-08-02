package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.department.AddModeratorToDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DeleteUserEntityFromModeratorListRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.security.Principal;

interface DepartmentManagementService {

    @Transactional
    DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO);

    @Transactional
    void deleteEmployeeFromDepartment(DeleteEmployeeRequestDTO deleteEmployeeRequestDTO, EmployeeEntity employeeEntity);

    @Transactional
    void deleteDepartmentByName(Principal loggedUser, String departmentName) throws AccessDeniedException;

    @Transactional
    void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO);

    @Transactional
    void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity);

    @Transactional
    void deleteUserEntityFromModeratorList(Principal loggedUser, DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws AccessDeniedException;

}

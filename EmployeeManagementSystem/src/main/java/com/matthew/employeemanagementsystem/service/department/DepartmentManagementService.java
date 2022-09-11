package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.department.*;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNoPermissionException;

import java.security.Principal;

interface DepartmentManagementService {

    DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO);

    void deleteEmployeeFromDepartment(DeleteEmployeeRequestDTO deleteEmployeeRequestDTO, EmployeeEntity employeeEntity);

    void deleteDepartmentByName(Principal loggedUser, String departmentName) throws DepartmentNoPermissionException;

    void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO);

    void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity);

    void deleteUserEntityFromModeratorList(Principal loggedUser, DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws DepartmentNoPermissionException;

    DepartmentResponseDTO modifyDepartmentName(ModifyDepartmentRequestDTO requestDTO);

    void deleteUserFromAllModeratorsList(UserEntity userToDelete);
}

package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.department.*;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNoPermissionException;

import java.security.Principal;
import java.util.List;

public interface DepartmentFacade {
    DepartmentEntity getDepartmentEntity(String department);

    DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName);

    DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO);

    void deleteEmployeeFromDepartment(DeleteEmployeeRequestDTO requestDTO, EmployeeEntity employeeEntity);

    void deleteDepartmentByName(Principal loggedUser, String departmentName) throws DepartmentNoPermissionException;

    void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO);

    void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity);

    void deleteUserEntityFromModeratorList(Principal loggedUser, DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws DepartmentNoPermissionException;

    List<DepartmentResponseDTO> findAllDepartments(Principal loggedUser) throws DepartmentNoPermissionException;

    DepartmentResponseDTO modifyDepartmentName(ModifyDepartmentRequestDTO requestDTO);

    void deleteUserFromAllModeratorsList(UserEntity userToDelete);

}

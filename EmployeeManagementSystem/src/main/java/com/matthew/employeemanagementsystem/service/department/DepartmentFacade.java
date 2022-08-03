package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.department.*;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNoPermissionException;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

public interface DepartmentFacade {
    DepartmentEntity getDepartmentEntity(String department);

    DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName);

    @Transactional
    DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO);

    @Transactional
    void deleteEmployeeFromDepartment(DeleteEmployeeRequestDTO requestDTO, EmployeeEntity employeeEntity);

    @Transactional
    void deleteDepartmentByName(Principal loggedUser, String departmentName) throws DepartmentNoPermissionException;

    @Transactional
    void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO);

    @Transactional
    void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity);

    @Transactional
    void deleteUserEntityFromModeratorList(Principal loggedUser, DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws DepartmentNoPermissionException;

    List<DepartmentDTOForObjectMapper> findAllDepartments(Principal loggedUser) throws DepartmentNoPermissionException;

}

package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.department.*;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
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
    void deleteDepartmentByName(Principal loggedUser, String departmentName) throws AccessDeniedException;

    @Transactional
    void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO);

    @Transactional
    void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity);

    @Transactional
    void deleteUserEntityFromModeratorList(Principal loggedUser, DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws AccessDeniedException;
    List<DepartmentDTOForObjectMapper> findAllDepartments();

}

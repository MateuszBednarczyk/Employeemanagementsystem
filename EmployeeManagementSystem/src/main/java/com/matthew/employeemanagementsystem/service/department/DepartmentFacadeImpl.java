package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.department.*;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNoPermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
class DepartmentFacadeImpl implements DepartmentFacade {

    private final DepartmentFindingService departmentFindingService;
    private final DepartmentManagementService departmentManagementService;

    @Override
    public DepartmentEntity getDepartmentEntity(String departmentName) {
        return departmentFindingService.getDepartmentEntity(departmentName);
    }

    @Override
    public DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName) {
        return departmentFindingService.findDepartmentEntityByNameAndReturnAsDTO(departmentName);
    }

    @Override
    public DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO) {
        return departmentManagementService.addNewDepartment(requestDTO);
    }

    @Override
    public void deleteEmployeeFromDepartment(DeleteEmployeeRequestDTO deleteEmployeeRequestDTO, EmployeeEntity employeeEntity) {
        departmentManagementService.deleteEmployeeFromDepartment(deleteEmployeeRequestDTO, employeeEntity);
    }

    @Override
    public void deleteDepartmentByName(Principal loggedUser, String departmentName) throws DepartmentNoPermissionException {
        departmentManagementService.deleteDepartmentByName(loggedUser, departmentName);
    }

    @Override
    public void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO) {
        departmentManagementService.addUserEntityToModeratorList(requestDTO);
    }

    @Override
    public void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity) {
        departmentManagementService.addDepartmentToEmployeeAndAddEmployeeToDepartment(selectedDepartment, newEmployeeEntity);
    }

    @Override
    public void deleteUserEntityFromModeratorList(Principal loggedUser, DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws DepartmentNoPermissionException {
        departmentManagementService.deleteUserEntityFromModeratorList(loggedUser, requestDTO);
    }

    @Override
    public List<DepartmentResponseDTO> findAllDepartments(Principal loggedUser) throws DepartmentNoPermissionException {
        return departmentFindingService.findAllDepartments(loggedUser);
    }
}

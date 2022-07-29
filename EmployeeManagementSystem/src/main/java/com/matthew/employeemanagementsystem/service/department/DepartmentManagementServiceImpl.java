package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.department.AddModeratorToDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import com.matthew.employeemanagementsystem.service.user.UserFindingService;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
class DepartmentManagementServiceImpl implements DepartmentManagementService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentFindingService departmentFindingService;
    private final UserFindingService userFindingService;

    @Override
    @Transactional
    public DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO) {
        checkIfAddingNewDepartmentIsPossible(requestDTO.departmentName());
        DepartmentEntity newDepartmentEntity = createAndSaveDepartmentEntity(requestDTO);

        return new DepartmentResponseDTO(newDepartmentEntity.getDepartmentName(), newDepartmentEntity.getEmployeesList());
    }

    @Override
    @Transactional
    public void deleteEmployeeFromDepartment(DeleteEmployeeRequestDTO deleteEmployeeRequestDTO, EmployeeEntity employeeEntity) {
        departmentRepository.findByDepartmentName(deleteEmployeeRequestDTO.departmentName()).ifPresent(departmentEntity -> {
            departmentEntity.getEmployeesList().remove(employeeEntity);
        });
    }

    @Override
    @Transactional
    public void deleteDepartmentByName(Principal loggedUser, String departmentName) throws AccessDeniedException {
        DepartmentEntity departmentEntity = departmentFindingService.getDepartmentEntity(departmentName);
        if (departmentEntity.getModeratorList().contains(userFindingService.getUserEntity(loggedUser.getName()))) {
            departmentRepository.deleteByDepartmentName(departmentName);
        } else {
            throw new AccessDeniedException("No permission");
        }
    }

    @Override
    @Transactional
    public void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO) {
        departmentFindingService.getDepartmentEntity(requestDTO.departmentName()).getModeratorList().add(userFindingService.getUserEntity(requestDTO.username()));
    }

    private DepartmentEntity createAndSaveDepartmentEntity(AddNewDepartmentRequestDTO requestDTO) {
        DepartmentEntity newDepartmentEntity = new DepartmentEntity(requestDTO.departmentName());
        departmentRepository.save(newDepartmentEntity);

        return newDepartmentEntity;
    }

    private void checkIfAddingNewDepartmentIsPossible(String departmentName) {
        checkIfVariablesNotNull(departmentName);
        departmentRepository.findByDepartmentName(departmentName).ifPresent(department -> {
            throw new IllegalArgumentException("Department already exists");
        });
    }

    private void checkIfVariablesNotNull(String departmentName) {
        if (departmentName == null) {
            throw new IllegalArgumentException("Unexpected variable");
        }
    }
}

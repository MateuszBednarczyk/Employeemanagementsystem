package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;
import com.matthew.employeemanagementsystem.dtos.department.AddModeratorToDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DeleteUserEntityFromModeratorListRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import com.matthew.employeemanagementsystem.service.role.RoleFacade;
import com.matthew.employeemanagementsystem.service.user.UserFindingService;
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
    private final RoleFacade roleFacade;

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
        departmentFindingService.getDepartmentEntity(deleteEmployeeRequestDTO.departmentName()).getEmployeesList().remove(employeeEntity);
    }

    @Override
    @Transactional
    public void deleteDepartmentByName(Principal loggedUser, String departmentName) throws AccessDeniedException {
        DepartmentEntity departmentEntity = departmentFindingService.getDepartmentEntity(departmentName);
        UserEntity userEntity = userFindingService.getUserEntity(loggedUser.getName());
        if (departmentEntity.getModeratorList().contains(userFindingService.getUserEntity(loggedUser.getName()))) {
            deleteRelationedData(userEntity, departmentEntity);
            departmentRepository.deleteByDepartmentName(departmentName);
        } else {
            throw new AccessDeniedException("No permission");
        }
    }

    private void deleteRelationedData(UserEntity userEntity, DepartmentEntity departmentEntity) {
        userEntity.getDepartmentEntities().remove(departmentEntity);
        departmentEntity.getModeratorList().remove(userEntity);
    }

    @Override
    @Transactional
    public void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO) {
        departmentFindingService.getDepartmentEntity(requestDTO.departmentName()).getModeratorList().add(userFindingService.getUserEntity(requestDTO.username()));
    }

    @Override
    @Transactional
    public void deleteUserEntityFromModeratorList(Principal loggedUser, DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws AccessDeniedException {
        UserEntity userEntity = userFindingService.getUserEntity(loggedUser.getName());
        if (userEntity.getRoles().contains(roleFacade.findByRoleType(RoleType.ROLE_ADMIN))) {
            deleteRelationedData(userFindingService.getUserEntity(requestDTO.username()),
                    departmentFindingService.getDepartmentEntity(requestDTO.departmentName()));
        } else {
            throw new AccessDeniedException("No perrmision");
        }
    }

    @Transactional
    public void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity) {
        newEmployeeEntity.getDepartmentEntities().add(selectedDepartment);
        selectedDepartment.getEmployeesList().add(newEmployeeEntity);
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

package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.enums.RoleType;
import com.matthew.employeemanagementsystem.dtos.department.*;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.exception.department.DepartmentAlreadyExistsException;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNoPermissionException;
import com.matthew.employeemanagementsystem.exception.user.UserIsAlredadyModeratorInDepartment;
import com.matthew.employeemanagementsystem.mapper.DepartmentModelMapper;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import com.matthew.employeemanagementsystem.service.user.UserFindingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
class DepartmentManagementServiceImpl implements DepartmentManagementService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentFindingService departmentFindingService;
    private final DepartmentModelMapper departmentModelMapper;
    private final UserFindingService userFindingService;

    @Override
    public DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO) {
        checkIfAddingNewDepartmentIsPossible(requestDTO.departmentName().toLowerCase());
        DepartmentEntity newDepartmentEntity = createAndSaveDepartmentEntity(requestDTO);

        return new DepartmentResponseDTO(newDepartmentEntity.getDepartmentName(), newDepartmentEntity.getEmployees());
    }

    @Override
    public void deleteEmployeeFromDepartment(DeleteEmployeeRequestDTO deleteEmployeeRequestDTO, EmployeeEntity employeeEntity) {
        departmentFindingService.getDepartmentEntity(deleteEmployeeRequestDTO.departmentName()).getEmployees().remove(employeeEntity);
    }

    @Override
    public void deleteDepartmentByName(Principal loggedUser, String departmentName) throws DepartmentNoPermissionException {
        DepartmentEntity departmentEntity = departmentFindingService.getDepartmentEntity(departmentName);
        UserEntity userEntity = userFindingService.getUserEntity(loggedUser.getName());
        if (isUserAModeratorInDepartment(departmentEntity, userFindingService.getUserEntity(loggedUser.getName())) || userEntity.getRole().equals(RoleType.ROLE_ADMIN)) {
            deleteRelatedData(userEntity, departmentEntity);
            departmentRepository.deleteByDepartmentName(departmentName);
        } else {
            throw new DepartmentNoPermissionException(departmentName);
        }
    }

    private void deleteRelatedData(UserEntity userEntity, DepartmentEntity departmentEntity) {
        userEntity.getDepartments().remove(departmentEntity);
        departmentEntity.getModerators().remove(userEntity);
    }

    @Override
    public void addUserEntityToModeratorList(AddModeratorToDepartmentRequestDTO requestDTO) {
        DepartmentEntity departmentEntity = departmentFindingService.getDepartmentEntity(requestDTO.departmentName());
        UserEntity userEntity = userFindingService.getUserEntity(requestDTO.username());
        if (isUserAModeratorInDepartment(departmentEntity, userEntity)) {
            throw new UserIsAlredadyModeratorInDepartment(requestDTO.username(), requestDTO.departmentName());
        }
        addUserAsAModeratorAndAddDepartmentEntityToUserIfItsNotPresent(departmentEntity, userEntity);
    }

    private void addUserAsAModeratorAndAddDepartmentEntityToUserIfItsNotPresent(DepartmentEntity departmentEntity, UserEntity userEntity) {
        departmentEntity.getModerators().add(userEntity);
        if (!userEntity.getDepartments().contains(departmentEntity)) {
            userEntity.getDepartments().add(departmentEntity);
        }
    }

    private boolean isUserAModeratorInDepartment(DepartmentEntity departmentEntity, UserEntity userEntity) {
        return departmentEntity.getModerators().contains(userEntity);
    }

    @Override
    public void deleteUserEntityFromModeratorList(Principal loggedUser, DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws DepartmentNoPermissionException {
        UserEntity userEntity = userFindingService.getUserEntity(loggedUser.getName());
        if (userEntity.getRole().equals(RoleType.ROLE_ADMIN) || userEntity.getRole().equals(RoleType.ROLE_SUPERADMIN)) {
            deleteRelatedData(userFindingService.getUserEntity(requestDTO.username()), departmentFindingService.getDepartmentEntity(requestDTO.departmentName()));
        } else {
            throw new DepartmentNoPermissionException(requestDTO.departmentName());
        }
    }

    @Override
    public DepartmentResponseDTO modifyDepartmentName(ModifyDepartmentRequestDTO requestDTO) {
        if (!requestDTO.repeatOldDepartmentName().equals(requestDTO.oldDepartmentName()) && requestDTO.repeatNewDepartmentName().equals(requestDTO.newDepartmentName())) {
            throw new IllegalArgumentException();
        }
        DepartmentEntity departmentEntity = departmentFindingService.getDepartmentEntity(requestDTO.oldDepartmentName());
        departmentEntity.setDepartmentName(requestDTO.newDepartmentName());

        return departmentModelMapper.mapDepartmentEntityToDepartmentResponseDTO(departmentEntity);
    }

    public void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity) {
        newEmployeeEntity.getDepartments().add(selectedDepartment);
        selectedDepartment.getEmployees().add(newEmployeeEntity);
    }

    private DepartmentEntity createAndSaveDepartmentEntity(AddNewDepartmentRequestDTO requestDTO) {
        DepartmentEntity newDepartmentEntity = new DepartmentEntity(requestDTO.departmentName().toLowerCase());
        departmentRepository.save(newDepartmentEntity);

        return newDepartmentEntity;
    }

    private void checkIfAddingNewDepartmentIsPossible(String departmentName) {
        checkIfVariablesNotNull(departmentName);
        departmentRepository.findByDepartmentName(departmentName).ifPresent(department -> {
            throw new DepartmentAlreadyExistsException(departmentName);
        });
    }

    private void checkIfVariablesNotNull(String departmentName) {
        if (departmentName == null) {
            throw new IllegalArgumentException("You can't pass null variables");
        }
    }
}

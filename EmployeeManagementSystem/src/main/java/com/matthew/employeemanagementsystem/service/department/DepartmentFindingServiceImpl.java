package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.enums.RoleType;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNoPermissionException;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNotFoundException;
import com.matthew.employeemanagementsystem.mapper.DepartmentModelMapper;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import com.matthew.employeemanagementsystem.service.user.UserFindingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class DepartmentFindingServiceImpl implements DepartmentFindingService {

    private final DepartmentRepository departmentRepository;
    private final UserFindingService userFindingService;
    private final DepartmentModelMapper departmentModelMapper;

    @Override
    public DepartmentEntity getDepartmentEntity(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName.toLowerCase()).orElseThrow(() -> new DepartmentNotFoundException(departmentName));
    }

    @Override
    public DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName) {
        DepartmentEntity foundEntity = getDepartmentEntity(departmentName);

        return departmentModelMapper.mapDepartmentEntityToDepartmentResponseDTO(foundEntity);
    }

    @Override
    public List<DepartmentResponseDTO> findAllDepartments(Principal loggedUser) throws DepartmentNoPermissionException {
        UserEntity loggedUserEntity = userFindingService.getUserEntity(loggedUser.getName());
        RoleType userRole = loggedUserEntity.getRole();
        if (userRole.equals(RoleType.ROLE_ADMIN) || userRole.equals(RoleType.ROLE_SUPERADMIN)) {

            return generateList(departmentRepository.findAll());

        } else if (userRole.equals(RoleType.ROLE_MODERATOR)) {

            return generateList(loggedUserEntity.getDepartmentEntities());

        } else {
            throw new DepartmentNoPermissionException();
        }
    }

    private List<DepartmentResponseDTO> generateList(List<DepartmentEntity> allowedDataSource) {
        List<DepartmentResponseDTO> responseDTOList = new ArrayList<>();
        for (DepartmentEntity departmentEntity : allowedDataSource) {
            responseDTOList.add(departmentModelMapper.mapDepartmentEntityToDepartmentResponseDTO(departmentEntity));
        }

        return responseDTOList;
    }

}

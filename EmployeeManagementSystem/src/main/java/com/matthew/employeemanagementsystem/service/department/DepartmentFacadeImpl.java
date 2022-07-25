package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public DepartmentResponseDTO findDepartmentEntityAndReturnAsDTO(String departmentName) {
        return departmentFindingService.findDepartmentEntityAndReturnAsDTO(departmentName);
    }

    @Override
    public DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO) {
        return departmentManagementService.addNewDepartment(requestDTO);
    }


}

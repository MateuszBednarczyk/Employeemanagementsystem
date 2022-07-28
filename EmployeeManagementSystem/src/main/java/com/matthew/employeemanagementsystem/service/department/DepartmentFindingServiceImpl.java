package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DepartmentFindingServiceImpl implements DepartmentFindingService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentEntity getDepartmentEntity(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName) {
        DepartmentEntity foundEntity = getDepartmentEntity(departmentName);

        return new DepartmentResponseDTO(foundEntity.getDepartmentName(), foundEntity.getEmployeesList());
    }
}

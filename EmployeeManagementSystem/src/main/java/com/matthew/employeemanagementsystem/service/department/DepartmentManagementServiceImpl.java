package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class DepartmentManagementServiceImpl implements DepartmentManagementService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentEntity getDepartmentEntity(String department) {
        return departmentRepository.findByDepartmentName(department).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public DepartmentResponseDTO addNewDepartment(AddNewDepartmentRequestDTO requestDTO) {
        DepartmentEntity departmentEntity = new DepartmentEntity(requestDTO.departmentName());
        departmentRepository.save(departmentEntity);

        return new DepartmentResponseDTO(departmentEntity.getDepartmentName(), departmentEntity.getEmployeesList());
    }
}

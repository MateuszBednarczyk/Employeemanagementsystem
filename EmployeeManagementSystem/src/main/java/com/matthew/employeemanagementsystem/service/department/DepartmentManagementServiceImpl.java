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
        checkIfDepartmentAlreadyExists(requestDTO.departmentName());
        DepartmentEntity newDepartmentEntity = new DepartmentEntity(requestDTO.departmentName());
        departmentRepository.save(newDepartmentEntity);

        return new DepartmentResponseDTO(newDepartmentEntity.getDepartmentName(), newDepartmentEntity.getEmployeesList());
    }

    private void checkIfDepartmentAlreadyExists(String departmentName) {
        departmentRepository.findByDepartmentName(departmentName).ifPresent(department -> {
            throw new RuntimeException("Department already exists");
        });
    }
}

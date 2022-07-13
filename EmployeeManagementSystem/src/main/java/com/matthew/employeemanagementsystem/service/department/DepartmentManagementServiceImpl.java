package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DepartmentManagementServiceImpl implements DepartmentManagementService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentEntity getDepartmentEntity(String department) {
        return departmentRepository.findByDepartment(department).orElseThrow(() -> new RuntimeException("DepartmentNotFound"));
    }

    @Override
    public ResponseEntity<String> addNewDepartment(AddNewDepartmentRequestDTO requestDTO) {
        DepartmentEntity departmentEntity = new DepartmentEntity(requestDTO.department());
        departmentRepository.save(departmentEntity);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }
}

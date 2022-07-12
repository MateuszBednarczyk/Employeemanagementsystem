package com.matthew.employeemanagementsystem.department;

import com.matthew.employeemanagementsystem.department.dtos.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.types.DepartmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DepartmentManagementServiceImpl implements DepartmentManagementService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentEntity getDepartmentEntity(DepartmentType departmentType) {
        return departmentRepository.findByDepartmentType(departmentType).orElseThrow(() -> new RuntimeException("DepartmentNotFound"));
    }

    @Override
    public ResponseEntity<String> addNewDepartment(AddNewDepartmentRequestDTO requestDTO) {
        DepartmentEntity departmentEntity = new DepartmentEntity(DepartmentType.valueOf(requestDTO.department()));
        departmentRepository.save(departmentEntity);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }
}

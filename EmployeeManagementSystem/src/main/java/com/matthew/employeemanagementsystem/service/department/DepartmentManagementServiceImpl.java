package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addNewDepartment(AddNewDepartmentRequestDTO requestDTO) {
        DepartmentEntity departmentEntity = new DepartmentEntity(requestDTO.departmentName());
        departmentRepository.save(departmentEntity);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }
}

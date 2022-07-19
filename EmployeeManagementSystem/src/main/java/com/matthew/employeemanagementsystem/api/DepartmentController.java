package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.service.department.DepartmentManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class DepartmentController {

    private final DepartmentManagementService departmentManagementService;

    @PostMapping("/api/department/add")
    public ResponseEntity<DepartmentResponseDTO> addNewDepartment(@RequestBody AddNewDepartmentRequestDTO requestDTO) {
        return new ResponseEntity<>(departmentManagementService.addNewDepartment(requestDTO), HttpStatus.OK);
    }

}

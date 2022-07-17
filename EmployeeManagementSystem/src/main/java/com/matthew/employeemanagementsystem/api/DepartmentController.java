package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.service.department.DepartmentManagementService;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class DepartmentController {

    private final DepartmentManagementService departmentManagementService;

    @PostMapping("/api/department/add")
    public ResponseEntity<String> addNewDepartment(@RequestBody AddNewDepartmentRequestDTO requestDTO) {
        return departmentManagementService.addNewDepartment(requestDTO);
    }

}

package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @PostMapping("/api/department/add")
    public ResponseEntity<DepartmentResponseDTO> addNewDepartment(@RequestBody AddNewDepartmentRequestDTO requestDTO) {
        return new ResponseEntity<>(departmentFacade.addNewDepartment(requestDTO), HttpStatus.OK);
    }

    @GetMapping("/api/department/{departmentName}")
    public ResponseEntity<DepartmentResponseDTO> findDepartmentEntityAndReturnAsDTO(@PathVariable String departmentName) {
        return new ResponseEntity<>(departmentFacade.findDepartmentEntityAndReturnAsDTO(departmentName), HttpStatus.OK);
    }

}

package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.department.AddModeratorToDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

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
        return new ResponseEntity<>(departmentFacade.findDepartmentEntityByNameAndReturnAsDTO(departmentName), HttpStatus.OK);
    }

    @PostMapping("api/department/delete/{departmentName}")
    public ResponseEntity<String> deleteDepartmentByName(Principal loggedUser, @PathVariable String departmentName) throws AccessDeniedException {
        departmentFacade.deleteDepartmentByName(loggedUser, departmentName);

        return new ResponseEntity<>(HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED);
    }

    @PostMapping("api/department/add-moderator")
    public ResponseEntity<String> addUserEntityToModeratorList(@RequestBody AddModeratorToDepartmentRequestDTO requestDTO) {
        departmentFacade.addUserEntityToModeratorList(requestDTO);

        return new ResponseEntity<>(HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED);
    }

}

package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.department.*;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department/")
class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @PostMapping("add")
    public ResponseEntity<DepartmentResponseDTO> addNewDepartment(@RequestBody AddNewDepartmentRequestDTO requestDTO) {
        return new ResponseEntity<>(departmentFacade.addNewDepartment(requestDTO), HttpStatus.OK);
    }

    @GetMapping("{departmentName}")
    public ResponseEntity<DepartmentResponseDTO> findDepartmentEntityAndReturnAsDTO(@PathVariable String departmentName) {
        return new ResponseEntity<>(departmentFacade.findDepartmentEntityByNameAndReturnAsDTO(departmentName), HttpStatus.OK);
    }

    @DeleteMapping("delete/{departmentName}")
    public ResponseEntity<String> deleteDepartmentByName(Principal loggedUser, @PathVariable String departmentName) throws AccessDeniedException {
        departmentFacade.deleteDepartmentByName(loggedUser, departmentName);

        return new ResponseEntity<>(HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED);
    }

    @PostMapping("add-moderator")
    public ResponseEntity<String> addUserEntityToModeratorList(@RequestBody AddModeratorToDepartmentRequestDTO requestDTO) {
        departmentFacade.addUserEntityToModeratorList(requestDTO);

        return new ResponseEntity<>(HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED);
    }

    @PostMapping("delete-user-from-moderator-list")
    public ResponseEntity<String> deleteUserEntityFromModeratorList(Principal loggedUser, @RequestBody DeleteUserEntityFromModeratorListRequestDTO requestDTO) throws AccessDeniedException {
        departmentFacade.deleteUserEntityFromModeratorList(loggedUser, requestDTO);

        return new ResponseEntity<>(HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> findAllDepartments(Principal loggedUser) throws AccessDeniedException {
        return new ResponseEntity<>(departmentFacade.findAllDepartments(loggedUser), HttpStatus.OK);
    }

    @PatchMapping("modify")
    public ResponseEntity<DepartmentResponseDTO> modifyDepartmentName(@RequestBody ModifyDepartmentRequestDTO requestDTO) {
        return new ResponseEntity<>(departmentFacade.modifyDepartmentName(requestDTO), HttpStatus.ACCEPTED);
    }

}

package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.repository.EmployeeRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
class EmployeeManagementServiceImp implements EmployeeManagementService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentManagementService departmentManagementService;

    @Override
    public ResponseEntity<String> addNewEmployee(AddNewEmployeeRequestDTO requestDTO) {
        checkIfEmployeeAlreadyExists(requestDTO.name(), requestDTO.surname());
        createAndPrepareEmployeeEntityAndSaveChangesInDepartmentEntity(requestDTO);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeEntity> findEmployeeByNameAndSurname(String name, String surname) {
        return new ResponseEntity<>(employeeRepository.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new RuntimeException("Employee not found")), HttpStatus.OK);
    }

    @Transactional
    public void createAndPrepareEmployeeEntityAndSaveChangesInDepartmentEntity(AddNewEmployeeRequestDTO requestDTO) {
        EmployeeEntity newEmployeeEntity = new EmployeeEntity(requestDTO.name(), requestDTO.surname());
        DepartmentEntity selectedDepartment = departmentManagementService.getDepartmentEntity(requestDTO.departmentName());
        addDepartmentToEmployeeAndAddEmployeeToDepartment(selectedDepartment, newEmployeeEntity);
        employeeRepository.save(newEmployeeEntity);
    }

    @Transactional
    public void addDepartmentToEmployeeAndAddEmployeeToDepartment(DepartmentEntity selectedDepartment, EmployeeEntity newEmployeeEntity) {
        newEmployeeEntity.getDepartmentEntities().add(selectedDepartment);
        selectedDepartment.getEmployeesList().add(newEmployeeEntity);
    }

    private void checkIfEmployeeAlreadyExists(String name, String surname) {
        employeeRepository.findByNameAndSurname(name, surname).ifPresent(employee -> {
            throw new RuntimeException("Employee already exists");
        });
    }
}

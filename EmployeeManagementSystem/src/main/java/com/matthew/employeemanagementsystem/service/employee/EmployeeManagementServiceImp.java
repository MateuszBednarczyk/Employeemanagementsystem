package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.repository.EmployeeRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
class EmployeeManagementServiceImp implements EmployeeManagementService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentManagementService departmentManagementService;

    @Override
    public EmployeeResponseDTO checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(AddNewEmployeeRequestDTO requestDTO) {
        checkIfEmployeeAlreadyExists(requestDTO.name(), requestDTO.surname());

        return createAndPrepareEntitiesAlsoReturnEmployeeDTO(requestDTO);
    }

    @Override
    public EmployeeResponseDTO findEmployeeByNameAndSurname(String name, String surname) {
        EmployeeEntity foundEmployeeEntity = employeeRepository.findByNameAndSurname(name, surname).orElseThrow(() -> new RuntimeException("Employee not found"));

        return new EmployeeResponseDTO(foundEmployeeEntity.getName(), foundEmployeeEntity.getSurname(), foundEmployeeEntity.getDepartmentEntities());
    }

    @Override
    public void deleteEmployeeByNameAndSurname(DeleteEmployeeRequestDTO requestDTO) {
        employeeRepository.findByNameAndSurname(requestDTO.name(), requestDTO.surname()).ifPresent(employeeEntity -> {
            employeeRepository.deleteByNameAndSurname(requestDTO.name(), requestDTO.surname());
        });
    }

    @Transactional
    public EmployeeResponseDTO createAndPrepareEntitiesAlsoReturnEmployeeDTO(AddNewEmployeeRequestDTO requestDTO) {
        EmployeeEntity newEmployeeEntity = new EmployeeEntity(requestDTO.name(), requestDTO.surname());
        DepartmentEntity selectedDepartment = departmentManagementService.getDepartmentEntity(requestDTO.departmentName());
        addDepartmentToEmployeeAndAddEmployeeToDepartment(selectedDepartment, newEmployeeEntity);
        employeeRepository.save(newEmployeeEntity);

        return new EmployeeResponseDTO(newEmployeeEntity.getName(), newEmployeeEntity.getSurname(), newEmployeeEntity.getDepartmentEntities());
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

package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.dtos.employee.ModifyEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.exception.employee.EmployeeAlreadyExistsException;
import com.matthew.employeemanagementsystem.mapper.EmployeeModelMapper;
import com.matthew.employeemanagementsystem.repository.EmployeeRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
class EmployeeManagementServiceImp implements EmployeeManagementService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeFindingService employeeFindingService;
    private final DepartmentFacade departmentFacade;
    private final EmployeeModelMapper employeeModelMapper;

    @Override
    public EmployeeResponseDTO checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(AddNewEmployeeRequestDTO requestDTO) {
        checkIfEmployeeAlreadyExists(requestDTO.name(), requestDTO.surname(), requestDTO.departmentName());

        return createAndPrepareEntitiesAlsoReturnEmployeeDTO(requestDTO);
    }

    @Override
    public void deleteEmployeeByNameAndSurname(DeleteEmployeeRequestDTO requestDTO) {
        employeeFindingService.findEmployeeEntityByNameAndSurname(requestDTO.name(), requestDTO.surname());
        departmentFacade.deleteEmployeeFromDepartment(requestDTO, employeeFindingService.findEmployeeEntityByNameAndSurname(requestDTO.name(), requestDTO.surname()));
        employeeRepository.deleteByNameAndSurname(requestDTO.name(), requestDTO.surname());
    }

    @Override
    public void modifyEmployee(ModifyEmployeeRequestDTO requestDTO) {
        EmployeeEntity employeeEntity = employeeFindingService.findById(requestDTO.id());
        if (!employeeEntity.getName().equals(requestDTO.name())) {
            employeeEntity.setName(requestDTO.name());
        }
        if (!employeeEntity.getSurname().equals(requestDTO.surname())) {
            employeeEntity.setSurname(requestDTO.surname());
        }
    }

    public EmployeeResponseDTO createAndPrepareEntitiesAlsoReturnEmployeeDTO(AddNewEmployeeRequestDTO requestDTO) {
        EmployeeEntity newEmployeeEntity = new EmployeeEntity(requestDTO.name(), requestDTO.surname(), requestDTO.email());
        DepartmentEntity selectedDepartment = departmentFacade.getDepartmentEntity(requestDTO.departmentName());
        departmentFacade.addDepartmentToEmployeeAndAddEmployeeToDepartment(selectedDepartment, newEmployeeEntity);
        employeeRepository.save(newEmployeeEntity);

        return employeeModelMapper.mapEmployeeEntityToEmployeeResponseDTO(newEmployeeEntity);
    }

    private void checkIfEmployeeAlreadyExists(String name, String surname, String departmentName) {
        employeeRepository.findByNameAndSurname(name, surname).ifPresent(employee -> {
            throw new EmployeeAlreadyExistsException(name, surname, departmentName);
        });
    }
}

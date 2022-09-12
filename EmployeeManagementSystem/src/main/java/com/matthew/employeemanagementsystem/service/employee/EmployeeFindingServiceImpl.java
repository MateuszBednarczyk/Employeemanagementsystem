package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.exception.employee.EmployeeNotFoundException;
import com.matthew.employeemanagementsystem.mapper.EmployeeModelMapper;
import com.matthew.employeemanagementsystem.repository.EmployeeRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class EmployeeFindingServiceImpl implements EmployeeFindingService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeModelMapper employeeModelMapper;
    private final DepartmentFacade departmentFacade;

    @Override
    public EmployeeEntity findEmployeeEntityByNameAndSurname(String name, String surname) {
        return employeeRepository.findByNameAndSurname(name, surname).orElseThrow(() -> new EmployeeNotFoundException(name, surname));
    }

    @Override
    public EmployeeResponseDTO findEmployeeByNameAndSurnameAndReturnItAsDTO(String name, String surname) {
        EmployeeEntity foundEmployeeEntity = employeeRepository.findByNameAndSurname(name, surname).orElseThrow(() -> new EmployeeNotFoundException(name, surname));

        return employeeModelMapper.mapEmployeeEntityToEmployeeResponseDTO(foundEmployeeEntity);
    }

    @Override
    public List<EmployeeResponseDTO> findEmployeesInDepartment(String departmentName) {
        return prepareEmployeeResponseDTOsList(findDepartmentEntity(departmentName));
    }

    @Override
    public EmployeeEntity findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private List<EmployeeEntity> findDepartmentEntity(String departmentName) {
        return departmentFacade.getDepartmentEntity(departmentName).getEmployees();
    }

    private List<EmployeeResponseDTO> prepareEmployeeResponseDTOsList(List<EmployeeEntity> employeeEntitiesList) {
        List<EmployeeResponseDTO> employeeResponseDTOsList = new ArrayList<>();
        employeeEntitiesList.forEach(employeeEntity -> employeeResponseDTOsList.add(employeeModelMapper.mapEmployeeEntityToEmployeeResponseDTO(employeeEntity)));
        return employeeResponseDTOsList;
    }
}

package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.exception.employee.EmployeeNotFoundException;
import com.matthew.employeemanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EmployeeFindingServiceImpl implements EmployeeFindingService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity findEmployeeEntityByNameAndSurname(String name, String surname) {
        return employeeRepository.findByNameAndSurname(name, surname).orElseThrow(() -> new EmployeeNotFoundException(name, surname));
    }

    @Override
    public EmployeeResponseDTO findEmployeeByNameAndSurnameAndReturnItAsDTO(String name, String surname) {
        EmployeeEntity foundEmployeeEntity = employeeRepository.findByNameAndSurname(name, surname).orElseThrow(() -> new EmployeeNotFoundException(name, surname));

        return new EmployeeResponseDTO(foundEmployeeEntity.getName(), foundEmployeeEntity.getSurname(), foundEmployeeEntity.getDepartmentEntities());
    }
}

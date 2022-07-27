package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EmployeeFindingServiceImpl implements EmployeeFindingService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO findEmployeeByNameAndSurname(String name, String surname) {
        EmployeeEntity foundEmployeeEntity = employeeRepository.findByNameAndSurname(name, surname).orElseThrow(() -> new RuntimeException("Employee not found"));

        return new EmployeeResponseDTO(foundEmployeeEntity.getName(), foundEmployeeEntity.getSurname(), foundEmployeeEntity.getDepartmentEntities());
    }
}

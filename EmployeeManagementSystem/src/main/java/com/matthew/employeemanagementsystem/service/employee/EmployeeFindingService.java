package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;

interface EmployeeFindingService {

    EmployeeResponseDTO findEmployeeByNameAndSurname(String name, String surname);
}

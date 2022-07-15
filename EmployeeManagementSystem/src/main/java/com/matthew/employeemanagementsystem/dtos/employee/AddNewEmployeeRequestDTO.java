package com.matthew.employeemanagementsystem.dtos.employee;

import org.hibernate.annotations.Immutable;

@Immutable
public record AddNewEmployeeRequestDTO(String name, String surname, String departmentName) {
}

package com.matthew.employeemanagementsystem.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @ManyToMany
    private List<DepartmentEntity> departmentEntities = new ArrayList<>();

    public EmployeeEntity(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

package com.matthew.employeemanagementsystem.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String department;

    public DepartmentEntity(String department) {
        this.department = department;
    }

    @ManyToMany
    private List<EmployeeEntity> employees = new ArrayList<>();

    @ManyToMany
    private List<UserEntity> moderatorList = new ArrayList<>();

    public DepartmentEntity() {

    }
}

package com.matthew.employeemanagementsystem.domain.entities;

import com.matthew.employeemanagementsystem.domain.types.RoleType;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @ManyToMany
    private List<DepartmentEntity> departmentEntities = new ArrayList<>();

}

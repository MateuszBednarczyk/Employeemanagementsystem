package com.matthew.employeemanagementsystem.domain.entities;

import com.matthew.employeemanagementsystem.domain.types.DepartmentType;
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
    private DepartmentType departmentType;

    public DepartmentEntity(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    @ManyToMany
    private List<EmployeeEntity> employees = new ArrayList<>();

    @ManyToMany
    private List<UserEntity> moderatorList = new ArrayList<>();

    public DepartmentEntity() {

    }
}

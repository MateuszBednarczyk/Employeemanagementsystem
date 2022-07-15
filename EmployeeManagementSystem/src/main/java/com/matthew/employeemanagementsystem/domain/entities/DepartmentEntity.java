package com.matthew.employeemanagementsystem.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String departmentName;

    public DepartmentEntity(String departmentName) {
        this.departmentName = departmentName;
    }

    @JsonIgnore
    @ManyToMany
    private List<EmployeeEntity> employeesList = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<UserEntity> moderatorList = new ArrayList<>();

}

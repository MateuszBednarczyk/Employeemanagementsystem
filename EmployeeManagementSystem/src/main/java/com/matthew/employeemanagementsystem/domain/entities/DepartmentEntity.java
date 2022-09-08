package com.matthew.employeemanagementsystem.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 25, message = "Department name has to be between 2 and 25 characters")
    private String departmentName;

    @JsonIgnore
    @ManyToMany
    private List<EmployeeEntity> employees = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<UserEntity> moderators = new ArrayList<>();

    public DepartmentEntity(String departmentName) {
        this.departmentName = departmentName;
    }

}

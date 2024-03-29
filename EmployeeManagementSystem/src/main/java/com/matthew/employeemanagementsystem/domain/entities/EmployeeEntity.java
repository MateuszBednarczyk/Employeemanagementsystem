package com.matthew.employeemanagementsystem.domain.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "Surname cannot be null")
    private String surname;

    @NotBlank(message = "E-Mail cannot be null")
    private String email;

    @ManyToMany
    private List<DepartmentEntity> departments = new ArrayList<>();

    public EmployeeEntity(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}

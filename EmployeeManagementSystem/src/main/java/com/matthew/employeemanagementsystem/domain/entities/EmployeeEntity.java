package com.matthew.employeemanagementsystem.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 1, max = 50, message = "Name has to be between 1 and 50 characters")
    private String name;

    @NotBlank
    @Size(min = 1, max = 60, message = "Surname has to be between 1 and 50 characters")
    private String surname;

    @NotBlank
    @Size(min = 1, max = 100, message = "Email has to be between 1 and 100 characters")
    private String email;

    @ManyToMany
    private List<DepartmentEntity> departments = new ArrayList<>();

    public EmployeeEntity(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}

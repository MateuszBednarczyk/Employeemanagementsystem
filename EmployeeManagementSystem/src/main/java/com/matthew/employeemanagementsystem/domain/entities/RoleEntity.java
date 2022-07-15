package com.matthew.employeemanagementsystem.domain.entities;

import com.matthew.employeemanagementsystem.domain.types.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private RoleType roleType;

}

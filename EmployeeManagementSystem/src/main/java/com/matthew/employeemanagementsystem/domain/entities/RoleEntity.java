package com.matthew.employeemanagementsystem.domain.entities;

import com.matthew.employeemanagementsystem.domain.enums.RoleType;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private RoleType roleType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleType == that.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleType);
    }
}

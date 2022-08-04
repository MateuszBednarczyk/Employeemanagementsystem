package com.matthew.employeemanagementsystem.dtos.user;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Immutable
public class UserResponseDTO {
    private String username;
    private List<DepartmentEntity> departmentList;
    private List<RoleEntity> roles;
}

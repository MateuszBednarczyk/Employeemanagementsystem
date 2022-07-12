package com.matthew.employeemanagementsystem.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}

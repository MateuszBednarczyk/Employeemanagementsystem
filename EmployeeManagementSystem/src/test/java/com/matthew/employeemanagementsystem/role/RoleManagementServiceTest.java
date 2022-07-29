package com.matthew.employeemanagementsystem.role;

import com.matthew.employeemanagementsystem.EmployeeManagementSystemApplication;
import com.matthew.employeemanagementsystem.service.role.RoleManagementService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
class RoleManagementServiceTest {

    @Autowired
    private RoleManagementService roleManagementService;

    @SneakyThrows
    @Test
    void shouldReturnEntity() {
        //given
        String roleName = "ROLE_MODERATOR";
        //when
        //then
        Assertions.assertEquals(roleName, roleManagementService.createRoleEntity(roleName).getRoleType().name());
    }

}

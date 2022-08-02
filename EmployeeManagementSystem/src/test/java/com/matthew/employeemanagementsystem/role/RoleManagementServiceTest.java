package com.matthew.employeemanagementsystem.role;

import com.matthew.employeemanagementsystem.EmployeeManagementSystemApplication;
import com.matthew.employeemanagementsystem.service.role.RoleFacade;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
class RoleManagementServiceTest {

    @Autowired
    private RoleFacade roleFacade = mock(RoleFacade.class);

    @SneakyThrows
    @Test
    void shouldReturnEntity() {
        //given
        String roleName = "ROLE_MODERATOR";
        //when
        //then
        Assertions.assertEquals(roleName, roleFacade.createRoleEntity(roleName).getRoleType().name());
    }

}

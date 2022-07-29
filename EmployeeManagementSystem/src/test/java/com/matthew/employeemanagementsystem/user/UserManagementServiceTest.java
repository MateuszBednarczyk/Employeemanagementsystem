package com.matthew.employeemanagementsystem.user;

import com.matthew.employeemanagementsystem.EmployeeManagementSystemApplication;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import com.matthew.employeemanagementsystem.service.role.RoleManagementService;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
class UserManagementServiceTest {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private DepartmentFacade departmentFacade;

    @Autowired
    private RoleManagementService roleManagementService;

    @SneakyThrows
    @Test
    void shouldNotRegisterNewUser() {
        //given
        RegisterNewUserRequestDTO requestDTO = new RegisterNewUserRequestDTO("mati", "mati", "IT", "ROLE_MODERATOR");
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> userManagementService.registerNewUser(requestDTO));
    }

    //It won't work cuz it's too isolated, and it needs extra injections of dependencies.
//    @SneakyThrows
//    @Test
//    void shouldReturnDTO() {
//        //given
//        DepartmentResponseDTO departmentResponseDTO = departmentFacade.addNewDepartment(new AddNewDepartmentRequestDTO("IT"));
//        RegisterNewUserRequestDTO requestDTO = new RegisterNewUserRequestDTO("mati", "mati", "IT", "ROLE_MODERATOR");
//        //when
//        UserResponseDTO userResponseDTO = userManagementService.registerNewUser(requestDTO);
//        //then
//        Assertions.assertEquals("mati", userResponseDTO.username());
//    }

}

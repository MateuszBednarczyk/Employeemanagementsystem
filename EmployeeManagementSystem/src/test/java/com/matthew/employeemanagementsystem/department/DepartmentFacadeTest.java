package com.matthew.employeemanagementsystem.department;


import com.matthew.employeemanagementsystem.EmployeeManagementSystemApplication;
import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.department.AddModeratorToDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.DeleteEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import com.matthew.employeemanagementsystem.service.employee.EmployeeFacade;
import com.matthew.employeemanagementsystem.service.role.RoleFacade;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
class DepartmentFacadeTest {

    private String exampleDepartmentName = "IT";
    private DepartmentResponseDTO departmentResponseDTO;
    private AddNewDepartmentRequestDTO addNewDepartmentRequestDTO = new AddNewDepartmentRequestDTO(exampleDepartmentName);
    String exampleName = "mati";
    String examplePassword = "mati";
    String exampleSurname = "matiowski";
    static String exampleRoleName = "ROLE_ADMIN";
    EmployeeEntity employeeEntity = new EmployeeEntity(exampleName, exampleSurname);
    RegisterNewUserRequestDTO registerNewUserRequestDTO = new RegisterNewUserRequestDTO(exampleName, examplePassword, exampleDepartmentName, exampleRoleName);

    @Autowired
    private DepartmentFacade departmentFacade = mock(DepartmentFacade.class);

    @Autowired
    private UserManagementService userManagementService = mock(UserManagementService.class);

    @Autowired
    private static RoleFacade RoleFacade = mock(RoleFacade.class);

    @Autowired
    private EmployeeFacade employeeFacade = mock(EmployeeFacade.class);

    Principal principal = new Principal() {

        String name = exampleName;

        @Override
        public String getName() {
            return name;
        }
    };

    @SneakyThrows
    @BeforeAll
    static void prepareRole() {
        RoleFacade.createRoleEntity(exampleRoleName);
    }

    @Test
    void shouldReturnDepartmentDTO() {
        //given
        addNewDepartmentRequestDTO = new AddNewDepartmentRequestDTO(exampleDepartmentName);
        //when
        departmentResponseDTO = departmentFacade.addNewDepartment(addNewDepartmentRequestDTO);
        //then
        Assertions.assertEquals(exampleDepartmentName, departmentResponseDTO.departmentName());
    }

    @Test
    void shouldGetDepartmentDtoFromDatabase() {
        //given
        //when
        departmentResponseDTO = departmentFacade.findDepartmentEntityByNameAndReturnAsDTO(exampleDepartmentName);
        //then
        Assertions.assertEquals(addNewDepartmentRequestDTO.departmentName(), departmentResponseDTO.departmentName());
    }

    @SneakyThrows
    @Test
    @Transactional
    void shouldAddModeratorToModeratorList() {
        //given
        AddModeratorToDepartmentRequestDTO addModeratorToDepartmentRequestDTO = new AddModeratorToDepartmentRequestDTO(exampleName, exampleDepartmentName);
        //when
        userManagementService.registerNewUser(registerNewUserRequestDTO);
        departmentFacade.addUserEntityToModeratorList(addModeratorToDepartmentRequestDTO);
        //then
        List<UserEntity> moderatorList = departmentFacade.getDepartmentEntity(exampleDepartmentName).getModeratorList();
        assertThat(moderatorList, hasSize(1));
    }

    @Test
    @Transactional
    void shouldAddEmployeeToDepartment() {
        //given
        DepartmentEntity departmentEntity = departmentFacade.getDepartmentEntity(exampleDepartmentName);
        AddNewEmployeeRequestDTO addNewEmployeeRequestDTO = new AddNewEmployeeRequestDTO(exampleName, exampleSurname, exampleDepartmentName);
        //when
        employeeFacade.checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(addNewEmployeeRequestDTO);
        //then
        Assertions.assertDoesNotThrow(() -> departmentFacade.addDepartmentToEmployeeAndAddEmployeeToDepartment(departmentEntity, employeeEntity));
    }

    @Test
    void shouldDeleteEmployeeFromDepartment() {
        //given
        DeleteEmployeeRequestDTO deleteEmployeeRequestDTO = new DeleteEmployeeRequestDTO(exampleName, exampleSurname, exampleDepartmentName);
        //when
        departmentResponseDTO = departmentFacade.addNewDepartment(addNewDepartmentRequestDTO);
        //then
        Assertions.assertDoesNotThrow(() -> departmentFacade.deleteEmployeeFromDepartment(deleteEmployeeRequestDTO, employeeEntity));
    }

    @SneakyThrows
    @Test
    void shouldDeleteDepartmentFromDatabase() {
        //given
        AddModeratorToDepartmentRequestDTO addModeratorToDepartmentRequestDTO = new AddModeratorToDepartmentRequestDTO(exampleName, exampleDepartmentName);
        //when
        userManagementService.registerNewUser(registerNewUserRequestDTO);
        departmentFacade.addUserEntityToModeratorList(addModeratorToDepartmentRequestDTO);
        //then
        Assertions.assertDoesNotThrow(() -> departmentFacade.deleteDepartmentByName(principal, exampleDepartmentName));
    }

}

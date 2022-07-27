package com.matthew.employeemanagementsystem;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.employee.AddNewEmployeeRequestDTO;
import com.matthew.employeemanagementsystem.dtos.employee.EmployeeResponseDTO;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import com.matthew.employeemanagementsystem.service.employee.EmployeeManagementService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
class DepartmentManagementServiceTest {

    private DepartmentEntity departmentEntity;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeManagementService employeeManagementService;

    @BeforeEach
    @Transactional
    void setupEntity() {
        departmentEntity = new DepartmentEntity("IT");
        departmentRepository.save(departmentEntity);
    }

    @AfterEach
    @Transactional
    void deleteEntity() {
        departmentRepository.delete(departmentEntity);
    }

    @Test
    @Transactional
    void shouldAddEmployeeToDepartmentAndReturnEmployeesInDepartment() {
        //given
        AddNewEmployeeRequestDTO requestDTO = new AddNewEmployeeRequestDTO("x", "y", "IT");
        //when
        EmployeeResponseDTO responseDTO = employeeManagementService.checkIfAddingEmployeeIsPossibleAndIfYesAddElseThrowException(requestDTO);
        //then
        assertThat(departmentEntity.getEmployeesList(), hasSize(1));
    }

}

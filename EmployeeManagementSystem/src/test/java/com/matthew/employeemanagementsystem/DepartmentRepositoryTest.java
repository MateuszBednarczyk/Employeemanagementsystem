package com.matthew.employeemanagementsystem;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    private DepartmentEntity departmentEntity;

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
    void shouldSaveNewDepartmentEntityInDatabase() {
        //given

        //when
        //then
        Optional<DepartmentEntity> entityGotFromDatabase = departmentRepository.findByDepartmentName(departmentEntity.getDepartmentName());
        assertThat(entityGotFromDatabase, notNullValue());
        assertEquals(entityGotFromDatabase.get().getDepartmentName(), departmentEntity.getDepartmentName());
    }

    @Test
    void shouldCreateNewDepartmentInDatabaseAndReturnItByName() {
        //given

        //when
        //then
        Optional<DepartmentEntity> entityGotFromDatabase = departmentRepository.findByDepartmentName(departmentEntity.getDepartmentName());
        assertEquals(entityGotFromDatabase.get().getDepartmentName(), departmentEntity.getDepartmentName());
    }

}
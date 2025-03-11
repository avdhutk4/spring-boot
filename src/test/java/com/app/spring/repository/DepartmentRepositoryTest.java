package com.app.spring.repository;

import com.app.spring.DepartmentMetaData;
import com.app.spring.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest extends DepartmentMetaData {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        testEntityManager.persist(getDepartmentRequest());
    }

    @Test
    void givenValidDepartmentNameWhenFindByDepartmentNameIgnoreCaseThenNoException() {

        Department department = departmentRepository.findByDepartmentNameIgnoreCase("cce");

        assertEquals(department.getDepartmentName(),getDepartment().getDepartmentName());
    }

    @Test
    void givenValidDepartmentIdWhenFindDepartmentByDepartmentIdThenNoException() {

        assertNotNull(departmentRepository.findByDepartmentId(1L));
    }
}
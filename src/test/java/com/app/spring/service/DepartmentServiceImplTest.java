package com.app.spring.service;

import com.app.spring.DepartmentMetaData;
import com.app.spring.entity.Department;
import com.app.spring.error.DepartmentNotDeletedSuccessfullyException;
import com.app.spring.error.DepartmentNotFoundException;
import com.app.spring.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(value = MockitoExtension.class)
class DepartmentServiceImplTest extends DepartmentMetaData {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenValidDepartmentWhenSaveDepartmentThenNoException() {
        Mockito.when(departmentRepository.save(ArgumentMatchers.any(Department.class))).thenReturn(getDepartment());
        Department department = departmentService.saveDepartment(getDepartmentRequest());

        assertEquals(department.getDepartmentName(),getDepartment().getDepartmentName());
    }

    @Test
    void givenInvalidDepartmentNameWhenSaveDepartmentThenNoExceptionOccurs() {
        Department department = getDepartmentRequest();
        department.setDepartmentName(null);
        assertNull(departmentService.saveDepartment(department));
    }

    @Test
    void givenNullDepartmentWhenSaveDepartmentThenNoException() {
        assertNull(departmentService.saveDepartment(null));

    }

    @Test
    void givenNothingWhenGetAllDepartmentsThenNoException() {
        Mockito.when(departmentRepository.findAll()).thenReturn(getMultipleDepartments());
        List<Department> departments = departmentService.getAllDepartments();
        assertEquals(getMultipleDepartments().size(),departments.size());
    }

    @Test
    void givenNothingWhenGetAllDepartmentsThenNoExceptionReturnsNull() {
        Mockito.when(departmentRepository.findAll()).thenReturn(null);
        List<Department> allDepartments = departmentService.getAllDepartments();
        assertNull(allDepartments);
    }

    @Test
    void givenValidDepartmentIdWhenGetDepartmentByDepartmentIdThenNoException() {
        Mockito.when(departmentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(getDepartment()));
        Department departmentByDepartmentId = departmentService.getDepartmentByDepartmentId(1L);

        assertEquals(departmentByDepartmentId.getDepartmentName(),getDepartment().getDepartmentName());
    }

    @Test
    void givenInvalidDepartmentIdWhenGetDepartmentByDepartmentIdThenExceptionRaised() {

        assertThrows(DepartmentNotFoundException.class,
                () -> departmentService.getDepartmentByDepartmentId(10L));
    }

    @Test
    void givenValidDepartmentIdWhenDeleteDepartmentThenDepartmentDeletedSuccessfully() {
        Mockito.when(departmentRepository.findById(ArgumentMatchers.anyLong()))
                        .thenReturn(Optional.ofNullable(getDepartment()))
                .thenReturn(Optional.empty());

        Mockito.doNothing().when(departmentRepository).delete(ArgumentMatchers.any(Department.class));

        String result = departmentService.deleteDepartmentByDepartmentId(1L);

        assertEquals("Department deleted successfully with departmentId " +1L, result);

    }

    @Test
    void givenInvalidDepartmentIdWhenDeleteDepartmentThenExceptionThrows() {
        assertThrows(DepartmentNotFoundException.class,
                () -> departmentService.deleteDepartmentByDepartmentId(1L));
    }

    @Test
    void givenValidDepartmentIdWhenDeleteDepartmentThenWhileDeletingRaisedException() {

        Mockito.when(departmentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(getDepartment()))
                .thenReturn(Optional.ofNullable(getDepartment()));

        Mockito.doNothing().when(departmentRepository).delete(ArgumentMatchers.any(Department.class));

        assertThrows(DepartmentNotDeletedSuccessfullyException.class,
                () -> departmentService.deleteDepartmentByDepartmentId(1L));
    }

    @Test
    void givenValidDepartmentIdAndDepartmentWhenUpdateDepartmentThenDepartmentUpdatedSuccessfully() {

        Mockito.when(departmentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(getDepartment()));

        Mockito.when(departmentRepository.save(ArgumentMatchers.any(Department.class)))
                .thenReturn(getDepartmentUpdated())
                .thenReturn(getDepartmentUpdated())
                .thenReturn(getDepartmentUpdated());

        Department department = departmentService.updateDepartment(1L,getDepartmentRequestForUpdateName());

        assertEquals(getDepartmentRequestForUpdateName().getDepartmentName(), department.getDepartmentName());

        assertNotNull(department.getDepartmentAddress());

        Department department1 = departmentService.updateDepartment(1L,
                getDepartmentRequestForUpdateNameAddress());

        assertEquals(getDepartmentRequestForUpdateNameAddress().getDepartmentName(),
                department1.getDepartmentName());

        assertEquals(getDepartmentRequestForUpdateNameAddress().getDepartmentAddress(),
                department1.getDepartmentAddress());

        Department department2 = departmentService.updateDepartment(1L,
                getDepartmentRequestForUpdateNameAddressCode());

        assertEquals(getDepartmentRequestForUpdateNameAddressCode().getDepartmentCode(),
                department2.getDepartmentCode());

        assertEquals(getDepartmentRequestForUpdateNameAddressCode().getDepartmentAddress(),
                department2.getDepartmentAddress());

    }

    @Test
    void givenInvalidDepartmentIdWhenUpdateDepartmentThenExceptionOccurred() {
        assertThrows(DepartmentNotFoundException.class,
                (() -> departmentService.updateDepartment(1L,getDepartment())));
    }


}
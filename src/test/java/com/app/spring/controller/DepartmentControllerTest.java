package com.app.spring.controller;

import com.app.spring.DepartmentMetaData;
import com.app.spring.entity.Department;
import com.app.spring.error.DepartmentNotFoundException;
import com.app.spring.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@ExtendWith(value = MockitoExtension.class)
@WebMvcTest(controllers = {DepartmentController.class})
class DepartmentControllerTest extends DepartmentMetaData {


    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private DepartmentService departmentService;

    /*@InjectMocks
    private DepartmentController departmentController;*/



    @BeforeEach
    void setUp() {
    }

    @Test
    void givenValidDepartmentWhenSaveDepartmentThenNoException() throws Exception {

        Mockito.when(departmentService.saveDepartment(ArgumentMatchers.any(Department.class)))
                .thenReturn(getDepartment());

        //assertDoesNotThrow(() -> departmentController.saveDepartment(getDepartmentRequest()));

        mvc.perform(MockMvcRequestBuilders.post("http://localhost:9696/departments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"Comp\",\n" +
                        "    \"departmentAddress\":\"Madras\",\n" +
                        "    \"departmentCode\":\"COMP-06\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentCode").value("CE-06"));
    }

    @Test
    void givenInvalidDepartmentWhenSaveDepartmentThenExceptionRaised() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/departments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentAddress\":\"Madras\",\n" +
                        "    \"departmentCode\":\"COMP-06\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenNothingWhenGetAllDepartmentsThenNoException() throws Exception {

        Mockito.when(departmentService.getAllDepartments()).thenReturn(getMultipleDepartments());

        mvc.perform(MockMvcRequestBuilders.get("/departments/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentCode").value("CE-06"));
    }

    @Test
    void givenValidDepartmentIdWhenGetDepartmentByDepartmentIdThenNoException() throws Exception {

        Mockito.when(departmentService.getDepartmentByDepartmentId(ArgumentMatchers.anyLong()))
                .thenReturn(getDepartment());

        mvc.perform(MockMvcRequestBuilders.get("/departments/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.
                                jsonPath("$.departmentAddress")
                                .value("Bangalore"));
    }

    @Test
    void givenInvalidDepartmentIdWhenGetDepartmentByDepartmentIdThenThrowsException() throws Exception {

        Mockito.when(departmentService.getDepartmentByDepartmentId(ArgumentMatchers.anyLong()))
                .thenThrow(new DepartmentNotFoundException());

        mvc.perform(MockMvcRequestBuilders.get("/departments/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void givenValidDepartmentIdAndDepartmentWhenUpdateDepartmentThenNoExceptionRaised() throws Exception {

        Mockito.when(departmentService.updateDepartment(ArgumentMatchers.anyLong(),ArgumentMatchers.any(Department.class)))
                .thenReturn(getDepartmentUpdated());

        mvc.perform(MockMvcRequestBuilders.put("/departments/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"Computer\",\n" +
                        "    \"departmentCode\":\"CMP-006\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value("Computer Science"));
    }

    @Test
    void givenInvalidDepartmentIdWhenUpdateDepartmentThenExceptionRaised() throws Exception {

        Mockito.when(departmentService.updateDepartment(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Department.class)))
                        .thenThrow(new DepartmentNotFoundException());

        mvc.perform(MockMvcRequestBuilders.put("/departments/"+ 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"Computer\",\n" +
                        "    \"departmentCode\":\"CMP-006\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void givenValidDepartmentIdWhenDeleteDepartmentThenNoException() throws Exception {

        Mockito.when(departmentService.deleteDepartmentByDepartmentId(ArgumentMatchers.anyLong()))
                .thenReturn("Department deleted successfully with departmentId " + 1);

        mvc.perform(MockMvcRequestBuilders.delete("/departments/" +1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Department deleted successfully with departmentId 1"));
    }



}
package com.app.spring.controller;

import com.app.spring.entity.Department;
import com.app.spring.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    //@Autowired
    private DepartmentService departmentService;

    private final Logger logger =
            LoggerFactory.getLogger(Department.class);

    /**
     *  Two type of Dependecy Injections are there
     *
     *  1. setter based dependency injection
     *  In the setter based we are creating the property and
     *  annotate with the @Autowired annotation.
     *
     *  2. constructor based dependency injection
     *  In the contructor based we are creating the constructor and assign this property on that
     *  constructor.
     * @param departmentService
     */
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * without having spring or spring boot we need to take request from httprequest and
     * convert json to java object by using one of the object mapper like
     * Gson of jackson but in spring or spring boot we can use directly @RequestBody
     * @param department
     * @return
     */
    @PostMapping(value = "/")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
        logger.info("Inside saveDepartment of DepartmentController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.saveDepartment(department));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        logger.info("Inside getAllDepartments of DepartmentController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.getAllDepartments());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Department> getDepartmentByDepartmentId(@PathVariable("id") Long departmentId){
        logger.info("Inside getDepartmentByDepartmentId of DepartmentController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.getDepartmentByDepartmentId(departmentId));

    }

    @DeleteMapping(value = "/{id}")
    public String deleteDepartment(@PathVariable("id") Long departmentId) {
        logger.info("Inside deleteDepartment of DepartmentController");
        return departmentService.deleteDepartmentByDepartmentId(departmentId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long departmentId,
                                                       @Valid @RequestBody Department department){
        logger.info("Inside updateDepartment of DepartmentController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.updateDepartment(departmentId,department));

    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Department> fetchDepartmentByDepartmentName(@PathVariable("name") String departmentName) {
        logger.info("Inside fetchDepartmentByDepartmentName of DepartmentController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.fetchDepartmentByDepartmentName(departmentName));
    }


}

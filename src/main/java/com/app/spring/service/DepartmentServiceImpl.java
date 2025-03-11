package com.app.spring.service;

import com.app.spring.entity.Department;
import com.app.spring.error.DepartmentNotDeletedSuccessfullyException;
import com.app.spring.error.DepartmentNotFoundException;
import com.app.spring.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    private final Logger logger =
            LoggerFactory.getLogger(DepartmentServiceImpl.class);

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Department saveDepartment(Department department) {
        logger.info("Inside saveDepartment of DepartmentServiceImpl {}",
                department);
		logger.info("Inside saveDepartment of DepartmentServiceImpl1 {}",
                department);
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Inside getAllDepartments of DepartmentServiceImpl");
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentByDepartmentId(Long departmentId) throws DepartmentNotFoundException {
        logger.info("Inside getDepartmentByDepartmentId of DepartmentServiceImpl");
        Optional<Department> department = departmentRepository.findById(departmentId);

        return department.orElseThrow(() -> new DepartmentNotFoundException("Department not available"));
    }

    @Override
    public String deleteDepartmentByDepartmentId(Long departmentId) {
        logger.info("Inside deleteDepartmentByDepartmentId of DepartmentServiceImpl");
        Department department = getDepartmentByDepartmentId(departmentId);
            departmentRepository.delete(department);
            if(departmentRepository.findById(departmentId).isPresent()) {
                throw new DepartmentNotDeletedSuccessfullyException("Error while deleting the department");
            }
            return "Department deleted successfully with departmentId " + departmentId;
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        logger.info("Inside updateDepartment of DepartmentServiceImpl");
        /**
         * requirement :
         * if one of the field is null or empty don't update that field
         * Only update if there is any value
         */
        Department oldDept = getDepartmentByDepartmentId(departmentId);

        if(Objects.nonNull(department.getDepartmentName()) && !"".equals(department.getDepartmentName())) {
            oldDept.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equals(department.getDepartmentAddress())) {
            oldDept.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) && !"".equals(department.getDepartmentCode())) {
            oldDept.setDepartmentCode(department.getDepartmentCode());
        }
		logger.info("Inside updateDepartment of DepartmentServiceImpl {}", oldDept);
        return departmentRepository.save(oldDept);
    }

    @Override
    public Department fetchDepartmentByDepartmentName(String departmentName) {
        logger.info("Inside fetchDepartmentByDepartmentName of DepartmentServiceImpl");
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}

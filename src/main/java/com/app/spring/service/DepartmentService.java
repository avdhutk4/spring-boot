package com.app.spring.service;

import com.app.spring.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentByDepartmentId(Long departmentId);

    String deleteDepartmentByDepartmentId(Long departmentId);

    Department updateDepartment(Long departmentId, Department department);

    Department fetchDepartmentByDepartmentName(String departmentName);
}

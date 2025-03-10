package com.app.spring;

import com.app.spring.entity.Department;

import java.util.List;

public class DepartmentMetaData {

    public Department getDepartment() {
        Department department = new Department(1L,
                "CCE",
                "Bangalore",
                "CE-06");
        return department;
    }

    public Department getDepartmentRequest() {
        Department department = new Department("CCE",
                "Bangalore",
                "CE-06");
        return department;
    }

    public Department getDepartmentRequestForUpdateName() {
        Department department = new Department("Computer Science");
        return department;
    }

    public Department getDepartmentRequestForUpdateNameAddress() {
        Department department = new Department("Computer Science",
                "Pondicherry");
        return department;
    }

    public Department getDepartmentRequestForUpdateNameAddressCode() {
        Department department = new Department("Computer Science",
                "Pondicherry",
                "CMP-006");
        return department;
    }

    public Department getDepartmentUpdated() {
        Department department = new Department(1L,
                "Computer Science",
                "Pondicherry",
                "CMP-006");
        return department;
    }

    public List<Department> getMultipleDepartments() {
        return List.of(new Department(
                1L,
                "CCE",
                "Bangalore",
                "CE-06"),
                new Department(2L,
                        "IIT",
                        "Ahmedabad",
                        "IT-07"));
    }
}

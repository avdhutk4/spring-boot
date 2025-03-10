package com.app.spring.repository;

import com.app.spring.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDepartmentNameIgnoreCase(String departmentName);

    Department findByDepartmentId(Long departmentId);
}

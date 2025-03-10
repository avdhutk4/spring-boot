package com.app.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotNull(message = "Department name should be there")
    @Size(max = 10,min = 3)
    //@Length(max=10,min=0)
//    @Positive
//    @PositiveOrZero
//    @Negative
//    @NegativeOrZero
    //@Email()
    //@Max(value = 9,message = "DepartmentName not moe than 9 characters")
    //@Min(value = 3,message = "more than 3 characters are required")
//    @Future
//    @Past
//    @FutureOrPresent
//    @PastOrPresent
    private String departmentName;

    private String departmentAddress;

    private String departmentCode;

    public Department() {
    }

    public Department(String departmentName, String departmentAddress, String departmentCode) {
        this.departmentName = departmentName;
        this.departmentAddress = departmentAddress;
        this.departmentCode = departmentCode;
    }

    public Department(Long departmentId, String departmentName, String departmentAddress, String departmentCode) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentAddress = departmentAddress;
        this.departmentCode = departmentCode;
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(String departmentName, String departmentAddress) {
        this.departmentName = departmentName;
        this.departmentAddress = departmentAddress;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentAddress='" + departmentAddress + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                '}';
    }
}

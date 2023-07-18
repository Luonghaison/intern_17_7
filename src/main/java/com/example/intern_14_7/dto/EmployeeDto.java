package com.example.intern_14_7.dto;

import com.example.intern_14_7.model.Department;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class EmployeeDto {
    private Long id;
    @NotEmpty(message = "Tên không được để trống")
    private String name;
    @Email(message = "Email không đúng định dạng")
    private String email;
    private Department department;
    private Set<String> role;
    private Long departmentId;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, String email, Department department, Set<String> role, Long departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.role = role;
        this.departmentId = departmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

}

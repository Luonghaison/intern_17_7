package com.example.intern_14_7.repository;

import com.example.intern_14_7.model.Employee;
import com.example.intern_14_7.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

package com.example.intern_14_7.service;

import com.example.intern_14_7.model.Employee;
import com.example.intern_14_7.model.Role;
import com.example.intern_14_7.repository.EmployeeRepository;
import com.example.intern_14_7.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}

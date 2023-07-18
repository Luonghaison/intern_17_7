package com.example.intern_14_7.service;
import com.example.intern_14_7.model.Employee;
import com.example.intern_14_7.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Page<Employee> findAll(String textSearch, Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> findAll(String textSearch) {
        return employeeRepository.findAll();
    }


    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return employeeRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

}


package com.example.intern_14_7.service;
import com.example.intern_14_7.model.Department;
import com.example.intern_14_7.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).get();
    }

    public void save(Department department) {
        departmentRepository.save(department);
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
    public Page<Department> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return departmentRepository.findByNameContainingIgnoreCase(name, pageable);
    }

}

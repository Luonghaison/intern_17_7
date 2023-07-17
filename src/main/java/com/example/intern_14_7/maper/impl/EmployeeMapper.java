package com.example.intern_14_7.maper.impl;
import com.example.intern_14_7.dto.EmployeeDto;
import com.example.intern_14_7.maper.EntityMaper;
import com.example.intern_14_7.model.Employee;
import com.example.intern_14_7.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EmployeeMapper implements EntityMaper<EmployeeDto, Employee> {
    @Override
    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        Employee entity = new Employee();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setDepartment(dto.getDepartment());
        entity.setDepartmentId(dto.getDepartmentId());
        return entity;
    }

    @Override
    public EmployeeDto toDto(Employee entity) {
        if (entity == null) {
            return null;
        }
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setDepartment(entity.getDepartment());
        Set<String> listRole = new HashSet<>();
        for (Role role : entity.getRoles()) {
            listRole.add(role.getName());
        }
        dto.setRole(listRole);
        dto.setDepartmentId(entity.getDepartmentId());
        return dto;
    }

    @Override
    public List<Employee> toEntity(List<EmployeeDto> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Employee> entityList = new ArrayList<>();
        for (EmployeeDto dto : dtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }

    @Override
    public List<EmployeeDto> toDto(List<Employee> entityList) {
        if (entityList == null) {
            return null;
        }
        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee entity : entityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

}

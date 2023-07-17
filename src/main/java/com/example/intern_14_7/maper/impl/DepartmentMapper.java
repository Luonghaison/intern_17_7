package com.example.intern_14_7.maper.impl;
import com.example.intern_14_7.dto.DepartmentDto;
import com.example.intern_14_7.maper.EntityMaper;
import com.example.intern_14_7.model.Department;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapper implements EntityMaper<DepartmentDto, Department> {
    @Override
    public Department toEntity(DepartmentDto dto) {
        if (dto == null) {
            return null;
        }
        Department entity = new Department();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public DepartmentDto toDto(Department entity) {
        if (entity == null) {
            return null;
        }
        DepartmentDto dto = new DepartmentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public List<Department> toEntity(List<DepartmentDto> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Department> entityList = new ArrayList<>();
        for (DepartmentDto dto : dtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }

    @Override
    public List<DepartmentDto> toDto(List<Department> entityList) {
        if (entityList == null) {
            return null;
        }
        List<DepartmentDto> dtoList = new ArrayList<>();
        for (Department entity : entityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

}

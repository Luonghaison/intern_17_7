package com.example.intern_14_7.maper;

import java.util.List;

public interface EntityMaper<D, E> {
    E toEntity(D dto);

    D toDto(E Entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> EntityList);

}

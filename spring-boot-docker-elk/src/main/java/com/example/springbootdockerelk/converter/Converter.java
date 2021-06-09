package com.example.springbootdockerelk.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bortnik
 */
public interface Converter<S, D> {

    D mapToEntity(S dto);
    S mapToDto(D entity);

    default List<D> mapToEntities(List<S> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    default List<S> mapToDtos(List<D> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}

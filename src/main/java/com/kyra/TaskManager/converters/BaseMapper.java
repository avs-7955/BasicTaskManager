package com.kyra.TaskManager.converters;

import com.kyra.TaskManager.dto.PagedResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseMapper<E, D> {
    D toDTO(E entity);

    E toEntity(D dto);

    List<D> toDTOs(List<E> entities);

    default PagedResponse<D> toPageDTO(Page<E> page) {
        List<D> dtoList = toDTOs(page.getContent());
        return new PagedResponse<>(
                dtoList,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}


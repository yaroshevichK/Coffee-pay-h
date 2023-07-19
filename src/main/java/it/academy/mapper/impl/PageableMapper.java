package it.academy.mapper.impl;

import it.academy.mapper.IMapper;
import it.academy.models.pageable.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PageableMapper<TEntity, TDto> implements IMapper<Pageable<TEntity>, Pageable<TDto>> {
    private final IMapper<TEntity, TDto> mapper;

    public PageableMapper(IMapper<TEntity, TDto> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Pageable<TEntity> dtoToEntity(Pageable<TDto> dto) {
        List<TEntity> records = Optional
                .ofNullable(dto.getRecords())
                .orElse(new ArrayList<>())
                .stream()
                .map(mapper::dtoToEntity)
                .collect(Collectors.toList());

        return Pageable.<TEntity>builder()
                .pageSize(dto.getPageSize())
                .lastPage(dto.getLastPage())
                .pageNumber(dto.getPageNumber())
                .sortField(dto.getSortField())
                .searchFields(dto.getSearchFields())
                .records(records)
                .build();
    }

    @Override
    public Pageable<TDto> entityToDto(Pageable<TEntity> entity) {
        List<TDto> records = Optional
                .ofNullable(entity.getRecords())
                .orElse(new ArrayList<>())
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());

        return Pageable.<TDto>builder()
                .pageSize(entity.getPageSize())
                .lastPage(entity.getLastPage())
                .pageNumber(entity.getPageNumber())
                .sortField(entity.getSortField())
                .searchFields(entity.getSearchFields())
                .records(records)
                .build();
    }
}

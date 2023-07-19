package it.academy.mapper;

public interface IMapper<TEntity, TDto> {
    TEntity dtoToEntity(TDto dto);

    TDto entityToDto(TEntity entity);
}

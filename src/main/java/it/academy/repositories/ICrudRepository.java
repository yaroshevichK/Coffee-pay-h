package it.academy.repositories;

import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface ICrudRepository<TEntity> {
    Pageable<TEntity> getPageableRecords(Integer pageSize,
                                         Integer pageNumber,
                                         HashMap<String, Object> searchFields,
                                         String sortField);

    boolean save(TEntity entity);

    TEntity findById(Serializable id);

    boolean update(TEntity entity);

    List<TEntity> findAll();

    boolean delete(Serializable id);
}

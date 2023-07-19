package it.academy.service;

import it.academy.dto.ModelDto;
import it.academy.models.pageable.Pageable;

import java.util.HashMap;
import java.util.List;

public interface IModelService {
    Pageable<ModelDto> getPageableRecords(Integer pageSize,
                                          Integer pageNumber,
                                          HashMap<String, Object> searchFields,
                                          String sortField);

    boolean createModel(ModelDto modelDto);

    ModelDto findModelById(Integer id);

    boolean updateModel(ModelDto modelDto);

    boolean deleteModelById(Integer id);

    List<ModelDto> findAllModels();
}

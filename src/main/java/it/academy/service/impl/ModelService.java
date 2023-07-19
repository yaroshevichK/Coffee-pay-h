package it.academy.service.impl;

import it.academy.dto.ModelDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.ModelMapper;
import it.academy.mapper.impl.PageableMapper;
import it.academy.models.Model;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IModelRepository;
import it.academy.repositories.impl.ModelRepository;
import it.academy.service.IModelService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelService implements IModelService {
    private final IModelRepository modelRepository = new ModelRepository();

    private final IMapper<Model, ModelDto> modelMapper = new ModelMapper();
    private final IMapper<Pageable<Model>, Pageable<ModelDto>> pageableMapper
            = new PageableMapper<>(modelMapper);

    @Override
    public Pageable<ModelDto> getPageableRecords(Integer pageSize,
                                                 Integer pageNumber,
                                                 HashMap<String, Object> searchFields,
                                                 String sortField) {
        Pageable<Model> pageable = modelRepository
                .getPageableRecords(pageSize,
                        pageNumber,
                        searchFields,
                        sortField);

        return pageableMapper.entityToDto(pageable);
    }

    @Override
    public boolean createModel(ModelDto modelDto) {
        Model model = modelMapper.dtoToEntity(modelDto);
        return modelRepository.save(model);
    }

    @Override
    public ModelDto findModelById(Integer id) {
        Model model = modelRepository.findById(id);
        return modelMapper.entityToDto(model);
    }

    @Override
    public boolean updateModel(ModelDto modelDto) {
        Model model = modelMapper.dtoToEntity(modelDto);
        return modelRepository.update(model);
    }

    @Override
    public boolean deleteModelById(Integer id) {
        return modelRepository.delete(id);
    }

    @Override
    public List<ModelDto> findAllModels() {
        List<Model> modelList = modelRepository.findAll();
        return Optional.ofNullable(modelList)
                .orElse(new ArrayList<>())
                .stream()
                .map(modelMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

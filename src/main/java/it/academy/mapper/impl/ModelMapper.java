package it.academy.mapper.impl;

import it.academy.dto.ModelDto;
import it.academy.mapper.IMapper;
import it.academy.models.Model;

public class ModelMapper implements IMapper<Model, ModelDto> {
    @Override
    public Model dtoToEntity(ModelDto modelDto) {
        return Model.builder()
                .id(modelDto.getId())
                .brand(modelDto.getBrand())
                .nameModel(modelDto.getNameModel())
                .build();
    }

    @Override
    public ModelDto entityToDto(Model model) {
        return ModelDto.builder()
                .id(model.getId())
                .brand(model.getBrand())
                .nameModel(model.getNameModel())
                .build();
    }
}

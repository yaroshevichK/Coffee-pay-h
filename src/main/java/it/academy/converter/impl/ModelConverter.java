package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.ModelDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MODEL_BRAND;
import static it.academy.utils.constants.DataUI.ATTR_MODEL_ID;
import static it.academy.utils.constants.DataUI.ATTR_MODEL_NAME_MODEL;

public class ModelConverter implements IConverter<ModelDto> {
    @Override
    public ModelDto convertToDto(HttpServletRequest request) {
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_MODEL_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        String brand = request.getParameter(ATTR_MODEL_BRAND);
        String nameModel = request.getParameter(ATTR_MODEL_NAME_MODEL);

        return ModelDto.builder()
                .id(id)
                .brand(brand)
                .nameModel(nameModel)
                .build();
    }
}

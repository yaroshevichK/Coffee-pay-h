package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.RoleDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_ROLE_ID;
import static it.academy.utils.constants.DataUI.ATTR_ROLE_NAME;

public class RoleConverter implements IConverter<RoleDto> {
    @Override
    public RoleDto convertToDto(HttpServletRequest request) {
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_ROLE_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        String name = request.getParameter(ATTR_ROLE_NAME);

        return RoleDto.builder()
                .id(id)
                .name(name)
                .build();
    }
}

package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.AddressDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_CITY;
import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_ID;
import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_STREET;

public class AddressConverter implements IConverter<AddressDto> {
    @Override
    public AddressDto convertToDto(HttpServletRequest request) {
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_ADDRESS_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        String city = request.getParameter(ATTR_ADDRESS_CITY);
        String street = request.getParameter(ATTR_ADDRESS_STREET);

        return AddressDto.builder()
                .id(id)
                .city(city)
                .street(street)
                .build();
    }
}

package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.CustomerDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_EMAIL;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_ID;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_NAME;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_PHONE;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_SURNAME;

public class CustomerConverter implements IConverter<CustomerDto> {
    @Override
    public CustomerDto convertToDto(HttpServletRequest request) {
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_CUSTOMER_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        String name = request.getParameter(ATTR_CUSTOMER_NAME);
        String surname = request.getParameter(ATTR_CUSTOMER_SURNAME);
        String phone = request.getParameter(ATTR_CUSTOMER_PHONE);
        String email = request.getParameter(ATTR_CUSTOMER_EMAIL);

        return CustomerDto.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .phone(phone)
                .email(email)
                .build();
    }
}

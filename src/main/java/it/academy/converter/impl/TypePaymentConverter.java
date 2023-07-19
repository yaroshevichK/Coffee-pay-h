package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.TypePaymentDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT_ID;
import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT_NAME;
import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT_USE_CARD;
import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT_USE_PHONE;
import static it.academy.utils.constants.DataUI.ON;

public class TypePaymentConverter implements IConverter<TypePaymentDto> {
    @Override
    public TypePaymentDto convertToDto(HttpServletRequest request) {
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_TYPE_PAYMENT_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        String name = request.getParameter(ATTR_TYPE_PAYMENT_NAME);
        String useCreditCard = request.getParameter(ATTR_TYPE_PAYMENT_USE_CARD);
        String usePhoneNumber = request.getParameter(ATTR_TYPE_PAYMENT_USE_PHONE);

        return TypePaymentDto.builder()
                .id(id)
                .name(name)
                .useCreditCard(ON.equals(useCreditCard))
                .usePhoneNumber(ON.equals(usePhoneNumber))
                .build();
    }
}

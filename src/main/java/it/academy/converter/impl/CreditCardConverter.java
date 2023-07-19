package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.CreditCardDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.constants.DataUI.ATTR_CREDIT_CARD_NUMBER;

public class CreditCardConverter implements IConverter<CreditCardDto> {
    @Override
    public CreditCardDto convertToDto(HttpServletRequest request) {
        String number = request.getParameter(ATTR_CREDIT_CARD_NUMBER);
        Integer creditCardId = Optional
                .ofNullable(request.getParameter(ATTR_CREDIT_CARD_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);


        return CreditCardDto.builder()
                .id(creditCardId)
                .number(number)
                .build();
    }
}

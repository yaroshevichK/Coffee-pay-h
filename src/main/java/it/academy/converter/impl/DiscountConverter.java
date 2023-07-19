package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.DiscountDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT_AMOUNT;
import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT_ID;
import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT_PERCENT;

public class DiscountConverter implements IConverter<DiscountDto> {
    @Override
    public DiscountDto convertToDto(HttpServletRequest request) {
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_DISCOUNT_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        Float percent = Optional
                .ofNullable(request.getParameter(ATTR_DISCOUNT_PERCENT))
                .filter(StringUtils::isNotBlank)
                .map(Float::parseFloat)
                .orElse(null);
        Integer amount = Optional
                .ofNullable(request.getParameter(ATTR_DISCOUNT_AMOUNT))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        return DiscountDto.builder()
                .id(id)
                .percent(percent)
                .amount(amount)
                .build();
    }
}

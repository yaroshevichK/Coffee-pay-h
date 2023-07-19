package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.PurchaseDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_PURCHASE_AMOUNT;
import static it.academy.utils.constants.DataUI.ATTR_PURCHASE_ID;
import static it.academy.utils.constants.DataUI.ATTR_PURCHASE_PRICE;

public class PurchaseConverter implements IConverter<PurchaseDto> {
    @Override
    public PurchaseDto convertToDto(HttpServletRequest request) {
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_PURCHASE_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        Float price = Optional
                .ofNullable(request.getParameter(ATTR_PURCHASE_PRICE))
                .filter(StringUtils::isNotBlank)
                .map(Float::parseFloat)
                .orElse(null);
        Float amount = Optional
                .ofNullable(request.getParameter(ATTR_PURCHASE_AMOUNT))
                .filter(StringUtils::isNotBlank)
                .map(Float::parseFloat)
                .orElse(null);

        return PurchaseDto.builder()
                .id(id)
                .amount(amount)
                .price(price)
                .build();
    }
}

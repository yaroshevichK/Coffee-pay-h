package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.ProductDto;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_NAME;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_PRICE;

public class ProductConverter implements IConverter<ProductDto> {
    @Override
    public ProductDto convertToDto(HttpServletRequest request) {
        String name = request.getParameter(ATTR_PRODUCT_NAME);
        String price = request.getParameter(ATTR_PRODUCT_PRICE);
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_PRODUCT_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        return ProductDto.builder()
                .id(id)
                .name(name)
                .price(StringUtils.isBlank(price) ? 0 : Float.parseFloat(price))
                .build();
    }
}

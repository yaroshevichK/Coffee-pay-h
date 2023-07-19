package it.academy.commands.discount;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.DiscountConverter;
import it.academy.dto.DiscountDto;
import it.academy.service.IDiscountService;
import it.academy.service.impl.DiscountService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT;
import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.EDIT_DISCOUNT_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateDiscount implements Command {
    private final IDiscountService discountService = new DiscountService();
    private final IConverter<DiscountDto> discountConverter = new DiscountConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (POST_REQUEST.equals(request.getMethod())) {
            return doPost(request);
        } else if (GET_REQUEST.equals(request.getMethod())) {
            return doGet(request);
        } else {
            request.setAttribute(ATTR_MESSAGE, ERROR_REQUEST);
            return ERROR_JSP;
        }
    }

    private String doGet(HttpServletRequest request) {
        Integer discountId = Optional
                .ofNullable(request.getParameter(ATTR_DISCOUNT_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        request.setAttribute(ATTR_DISCOUNT, discountService.findDiscountById(discountId));
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_DISCOUNT_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        DiscountDto discountDto = discountConverter.convertToDto(request);
        boolean isDiscountUpdate = discountService.updateDiscount(discountDto);

        if (!isDiscountUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

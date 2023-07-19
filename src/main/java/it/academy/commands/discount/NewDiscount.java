package it.academy.commands.discount;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.DiscountConverter;
import it.academy.dto.DiscountDto;
import it.academy.service.IDiscountService;
import it.academy.service.impl.DiscountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.NEW_DISCOUNT_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class NewDiscount implements Command {
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
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return NEW_DISCOUNT_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        DiscountDto discountDto = discountConverter.convertToDto(request);
        boolean isDiscountCreate = discountService.createDiscount(discountDto);

        if (!isDiscountCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

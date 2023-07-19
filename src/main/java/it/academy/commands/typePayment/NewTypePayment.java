package it.academy.commands.typePayment;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.TypePaymentConverter;
import it.academy.dto.TypePaymentDto;
import it.academy.service.ITypePaymentService;
import it.academy.service.impl.TypePaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.NEW_PAYMENT_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class NewTypePayment implements Command {
    private final ITypePaymentService typePaymentService = new TypePaymentService();
    private final IConverter<TypePaymentDto> typePaymentConverter = new TypePaymentConverter();

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
        return NEW_PAYMENT_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        TypePaymentDto typePaymentDto = typePaymentConverter.convertToDto(request);
        boolean isTypePaymentCreate = typePaymentService.createTypePayment(typePaymentDto);

        if (!isTypePaymentCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

package it.academy.commands.typePayment;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.TypePaymentConverter;
import it.academy.dto.TypePaymentDto;
import it.academy.service.ITypePaymentService;
import it.academy.service.impl.TypePaymentService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT;
import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT_ID;
import static it.academy.utils.constants.DataUI.EDIT_PAYMENT_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateTypePayment implements Command {
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
        Integer typePaymentId = Optional
                .ofNullable(request.getParameter(ATTR_TYPE_PAYMENT_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        request.setAttribute(ATTR_TYPE_PAYMENT, typePaymentService.findTypePaymentById(typePaymentId));
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PAYMENT_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        TypePaymentDto typePaymentDto = typePaymentConverter.convertToDto(request);
        boolean isTypePaymentUpdate = typePaymentService.updateTypePayment(typePaymentDto);

        if (!isTypePaymentUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

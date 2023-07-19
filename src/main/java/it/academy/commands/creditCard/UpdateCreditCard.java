package it.academy.commands.creditCard;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.CreditCardConverter;
import it.academy.dto.CreditCardDto;
import it.academy.service.ICreditCardService;
import it.academy.service.impl.CreditCardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CREDIT_CARD;
import static it.academy.utils.constants.DataUI.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.EDIT_CREDIT_CARD_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateCreditCard implements Command {
    private final ICreditCardService creditCardService = new CreditCardService();
    private final IConverter<CreditCardDto> creditCardConverter = new CreditCardConverter();

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
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_CREDIT_CARD_ID))
                .map(Integer::parseInt)
                .orElse(null);
        CreditCardDto creditCard = creditCardService.findCreditCardById(id);

        request.setAttribute(ATTR_CREDIT_CARD, creditCard);
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_CREDIT_CARD_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);
        CreditCardDto creditCard = creditCardConverter.convertToDto(request);
        Integer customerId = Optional
                .ofNullable(request.getParameter(ATTR_CUSTOMER_ID))
                .map(Integer::parseInt)
                .orElse(null);

        boolean isCreditCardUpdate =
                Optional.ofNullable(customerId)
                        .map(id -> creditCardService.updateCreditCard(creditCard, id))
                        .orElse(false);

        if (!isCreditCardUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

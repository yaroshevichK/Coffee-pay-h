package it.academy.commands.creditCard;

import it.academy.commands.Command;
import it.academy.service.ICreditCardService;
import it.academy.service.impl.CreditCardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class DeleteCreditCard implements Command {
    private final ICreditCardService creditCardService = new CreditCardService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer creditCardId = Optional
                .ofNullable(request.getParameter(ATTR_CREDIT_CARD_ID))
                .map(Integer::parseInt)
                .orElse(null);

        boolean isRoleDelete = creditCardService.deleteCreditCardById(creditCardId);

        if (!isRoleDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

package it.academy.commands.typePayment;

import it.academy.commands.Command;
import it.academy.service.ITypePaymentService;
import it.academy.service.impl.TypePaymentService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT_ID;
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class DeleteTypePayment implements Command {
    private final ITypePaymentService typePaymentService = new TypePaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer typePaymentId = Optional
                .ofNullable(request.getParameter(ATTR_TYPE_PAYMENT_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        boolean isTypePaymentDelete = typePaymentService.deleteTypePaymentById(typePaymentId);

        if (!isTypePaymentDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

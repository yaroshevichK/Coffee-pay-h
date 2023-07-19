package it.academy.commands.typePayment;

import it.academy.commands.Command;
import it.academy.service.ITypePaymentService;
import it.academy.service.impl.TypePaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.DataUI.ATTR_LIST_TYPE_PAYMENTS;
import static it.academy.utils.constants.DataUI.PAYMENTS_JSP;

public class GetTypePayments implements Command {
    private final ITypePaymentService typePaymentService = new TypePaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ATTR_LIST_TYPE_PAYMENTS, typePaymentService.findAllTypePayments());
        return PAYMENTS_JSP;
    }
}

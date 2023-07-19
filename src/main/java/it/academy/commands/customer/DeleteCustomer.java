package it.academy.commands.customer;

import it.academy.commands.Command;
import it.academy.service.ICustomerService;
import it.academy.service.impl.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class DeleteCustomer implements Command {
    private final ICustomerService customerService = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer customerId = Optional
                .ofNullable(request.getParameter(ATTR_CUSTOMER_ID))
                .map(Integer::parseInt)
                .orElse(null);

        boolean isCustomerDelete = customerService.deleteCustomerById(customerId);

        if (!isCustomerDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

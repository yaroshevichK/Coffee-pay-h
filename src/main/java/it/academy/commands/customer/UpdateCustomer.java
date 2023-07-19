package it.academy.commands.customer;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.CustomerConverter;
import it.academy.converter.impl.ModelConverter;
import it.academy.dto.CustomerDto;
import it.academy.dto.ModelDto;
import it.academy.service.ICustomerService;
import it.academy.service.IModelService;
import it.academy.service.impl.CustomerService;
import it.academy.service.impl.ModelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_MODEL;
import static it.academy.utils.constants.DataUI.ATTR_MODEL_ID;
import static it.academy.utils.constants.DataUI.EDIT_CUSTOMER_JSP;
import static it.academy.utils.constants.DataUI.EDIT_MODEL_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateCustomer implements Command {
    private final ICustomerService customerService = new CustomerService();
    private final IConverter<CustomerDto> customerConverter = new CustomerConverter();

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
        Integer customerId = Optional
                .ofNullable(request.getParameter(ATTR_CUSTOMER_ID))
                .map(Integer::parseInt)
                .orElse(null);
        CustomerDto customer = customerService.findCustomerById(customerId);

        request.setAttribute(ATTR_CUSTOMER, customer);
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_CUSTOMER_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        CustomerDto customerDto = customerConverter.convertToDto(request);
        boolean isCustomerUpdate = customerService.updateCustomer(customerDto);

        if (!isCustomerUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

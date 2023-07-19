package it.academy.commands.customer;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.CustomerConverter;
import it.academy.dto.CustomerDto;
import it.academy.dto.UserDto;
import it.academy.service.ICustomerService;
import it.academy.service.impl.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PASSWORD;
import static it.academy.utils.constants.DataUI.ATTR_USERNAME;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.ERROR_REGISTRATION;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.LOGGED_USER;
import static it.academy.utils.constants.DataUI.LOGIN_JSP;
import static it.academy.utils.constants.DataUI.MAIN_JSP;
import static it.academy.utils.constants.DataUI.NEW_CUSTOMER_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;
import static it.academy.utils.constants.DataUI.REGISTRATION_JSP;

public class NewCustomer implements Command {
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
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return NEW_CUSTOMER_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);
        String username = request.getParameter(ATTR_USERNAME);
        String password = request.getParameter(ATTR_PASSWORD);

        CustomerDto customer = customerConverter.convertToDto(request);
        boolean isCustomerCreate = customerService.createCustomer(username, password, customer);

        if (!isCustomerCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

package it.academy.commands;

import it.academy.converter.IConverter;
import it.academy.converter.impl.CustomerConverter;
import it.academy.dto.CustomerDto;
import it.academy.dto.UserDto;
import it.academy.service.ICustomerService;
import it.academy.service.impl.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.LOGGED_USER;
import static it.academy.utils.constants.DataUI.MAIN_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PROFILE_JSP;

public class Profile implements Command {
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
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(LOGGED_USER);

        CustomerDto customer = Optional.ofNullable(userDto)
                .map(UserDto::getUsername)
                .map(customerService::findCustomerByUsername)
                .orElse(null);

        request.setAttribute(ATTR_CUSTOMER, customer);

        return PROFILE_JSP;
    }

    private String doPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(LOGGED_USER);
        String username = Optional.ofNullable(userDto).map(UserDto::getUsername).orElse(null);

        CustomerDto customerDto = customerConverter.convertToDto(request);
        boolean isCustomerUpdate = customerService.updateCustomer(username, customerDto);

        if (!isCustomerUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return MAIN_JSP;
        }
    }
}

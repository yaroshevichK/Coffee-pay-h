package it.academy.commands.creditCard;

import it.academy.commands.Command;
import it.academy.dto.CustomerDto;
import it.academy.dto.UserDto;
import it.academy.service.ICreditCardService;
import it.academy.service.impl.CreditCardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CREDIT_CARD_NUMBER;
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
import static it.academy.utils.constants.DataUI.NEW_CREDIT_CARD_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class NewCreditCard implements Command {
    private final ICreditCardService creditCardService = new CreditCardService();

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
        return NEW_CREDIT_CARD_JSP;
    }

    private String doPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(LOGGED_USER);
        String prevUrl = request.getParameter(PREV_URL);
        String number = request.getParameter(ATTR_CREDIT_CARD_NUMBER);

        boolean isCardCreate = Optional.ofNullable(userDto)
                .map(UserDto::getUsername)
                .map(username -> creditCardService.createCreditCard(username,number))
                .orElse(false);

        if (!isCardCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

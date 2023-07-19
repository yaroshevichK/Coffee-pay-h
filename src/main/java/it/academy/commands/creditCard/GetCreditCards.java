package it.academy.commands.creditCard;

import it.academy.commands.Command;
import it.academy.dto.CreditCardDto;
import it.academy.dto.UserDto;
import it.academy.service.ICreditCardService;
import it.academy.service.impl.CreditCardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_LIST_CREDIT_CARDS;
import static it.academy.utils.constants.DataUI.CREDIT_CARDS_JSP;
import static it.academy.utils.constants.DataUI.LOGGED_USER;
import static it.academy.utils.constants.DataUI.PROFILE_JSP;

public class GetCreditCards implements Command {
    private final ICreditCardService creditCardService = new CreditCardService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(LOGGED_USER);

        List<CreditCardDto> creditCards = Optional.ofNullable(userDto)
                .map(UserDto::getUsername)
                .map(creditCardService::findCreditCardByUsername)
                .orElse(null);

        request.setAttribute(ATTR_LIST_CREDIT_CARDS, creditCards);

        return CREDIT_CARDS_JSP;
    }
}

package it.academy.commands.order;

import it.academy.commands.Command;
import it.academy.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static it.academy.utils.constants.DataUI.LOGGED_USER;
import static it.academy.utils.constants.DataUI.LOGIN_JSP;
import static it.academy.utils.constants.DataUI.MAIN_JSP;
import static it.academy.utils.constants.DataUI.MAIN_ORDER_JSP;

public class MainOrder implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        UserDto userDto = (UserDto) session.getAttribute(LOGGED_USER);
        if (userDto != null) {
            return MAIN_ORDER_JSP;
        } else {
            session.invalidate();
        }
        return LOGIN_JSP;
    }
}

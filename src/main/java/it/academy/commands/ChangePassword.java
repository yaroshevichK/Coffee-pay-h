package it.academy.commands;

import it.academy.commands.Command;
import it.academy.dto.UserDto;
import it.academy.service.IUserService;
import it.academy.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_OLD_PASSWORD;
import static it.academy.utils.constants.DataUI.ATTR_PASSWORD;
import static it.academy.utils.constants.DataUI.CHANGE_PASSWORD_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.LOGGED_USER;
import static it.academy.utils.constants.DataUI.MAIN_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;

public class ChangePassword implements Command {
    private final IUserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (POST_REQUEST.equals(request.getMethod())) {
            return doPost(request);
        } else if (GET_REQUEST.equals(request.getMethod())) {
            return doGet();
        } else {
            request.setAttribute(ATTR_MESSAGE, ERROR_REQUEST);
            return ERROR_JSP;
        }
    }

    private String doGet() {
        return CHANGE_PASSWORD_JSP;
    }

    private String doPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(LOGGED_USER);

        String username = Optional.ofNullable(userDto).map(UserDto::getUsername).orElse(null);
        String password = request.getParameter(ATTR_PASSWORD);
        String oldPassword = request.getParameter(ATTR_OLD_PASSWORD);

        boolean isPassUpdate = userService.updatePassUser(username, password, oldPassword);

        if (!isPassUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return MAIN_JSP;
        }

    }
}

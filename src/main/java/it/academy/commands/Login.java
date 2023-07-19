package it.academy.commands;

import it.academy.dto.RoleDto;
import it.academy.dto.UserDto;
import it.academy.service.IUserService;
import it.academy.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PASSWORD;
import static it.academy.utils.constants.DataUI.ATTR_USERNAME;
import static it.academy.utils.constants.DataUI.AUTHORITY;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_LOGIN;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.LOGGED_USER;
import static it.academy.utils.constants.DataUI.LOGIN_JSP;
import static it.academy.utils.constants.DataUI.MAIN_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;

public class Login implements Command {
    private final IUserService userService = new UserService();

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
        if (userDto != null) {
            return MAIN_JSP;
        } else {
            session.invalidate();
        }

        return LOGIN_JSP;
    }

    private String doPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(LOGGED_USER);
        if (userDto != null) {
            return MAIN_JSP;
        }

        String login = request.getParameter(ATTR_USERNAME);
        String password = request.getParameter(ATTR_PASSWORD);

        UserDto authUser = userService.findAuthUser(login, password);
        session.setAttribute(LOGGED_USER, authUser);

        if (authUser == null) {
            request.setAttribute(ATTR_MESSAGE, ERROR_LOGIN);
            return ERROR_JSP;
        } else {
            session.setAttribute(AUTHORITY,
                    authUser.getRoles()
                            .stream()
                            .map(RoleDto::getName)
                            .collect(Collectors.toList()));
            return MAIN_JSP;
        }
    }
}

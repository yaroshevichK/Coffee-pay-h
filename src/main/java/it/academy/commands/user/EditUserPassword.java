package it.academy.commands.user;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.RoleConverter;
import it.academy.dto.RoleDto;
import it.academy.service.IUserService;
import it.academy.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_OLD_PASSWORD;
import static it.academy.utils.constants.DataUI.ATTR_PASSWORD;
import static it.academy.utils.constants.DataUI.ATTR_USERNAME;
import static it.academy.utils.constants.DataUI.EDIT_PASS_USER_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class EditUserPassword implements Command {
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
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        request.setAttribute(ATTR_USERNAME, request.getParameter(ATTR_USERNAME));
        return EDIT_PASS_USER_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);
        String username = request.getParameter(ATTR_USERNAME);
        String password = request.getParameter(ATTR_PASSWORD);
        String oldPassword = request.getParameter(ATTR_OLD_PASSWORD);

        boolean isPassUpdate = userService.updatePassUser(username, password, oldPassword);

        if (!isPassUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

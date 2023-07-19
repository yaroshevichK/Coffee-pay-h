package it.academy.commands.user;

import it.academy.commands.Command;
import it.academy.repositories.IRoleRepository;
import it.academy.service.IRoleService;
import it.academy.service.IUserService;
import it.academy.service.impl.RoleService;
import it.academy.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashSet;

import static it.academy.utils.constants.DataUI.ATTR_CHECK_ROLES;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PASSWORD;
import static it.academy.utils.constants.DataUI.ATTR_ROLES;
import static it.academy.utils.constants.DataUI.ATTR_USERNAME;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.NEW_USER_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class NewUser implements Command {
    private final IUserService userService = new UserService();
    private final IRoleService roleService = new RoleService();

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
        request.setAttribute(ATTR_ROLES, roleService.findAllRoles());
        return NEW_USER_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String username = request.getParameter(ATTR_USERNAME);
        String password = request.getParameter(ATTR_PASSWORD);
        String[] roles = request.getParameterValues(ATTR_CHECK_ROLES);
        String prevUrl = request.getParameter(PREV_URL);

        boolean isUserCreate = userService.createUser(username, password, roles);

        if (!isUserCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

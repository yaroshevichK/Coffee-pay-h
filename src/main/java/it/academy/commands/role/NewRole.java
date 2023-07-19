package it.academy.commands.role;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.RoleConverter;
import it.academy.dto.RoleDto;
import it.academy.service.IRoleService;
import it.academy.service.impl.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PASSWORD;
import static it.academy.utils.constants.DataUI.ATTR_ROLE_NAME;
import static it.academy.utils.constants.DataUI.ATTR_USERNAME;
import static it.academy.utils.constants.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.constants.DataUI.EMPTY_STRING;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.ERROR_REGISTRATION;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.FIRST_PAGE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.LOGIN_JSP;
import static it.academy.utils.constants.DataUI.NEW_ROLE_JSP;
import static it.academy.utils.constants.DataUI.PAGE_NUMBER;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class NewRole implements Command {
    private final IRoleService roleService = new RoleService();
    private final IConverter<RoleDto> roleConverter = new RoleConverter();

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
        return NEW_ROLE_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        RoleDto role = roleConverter.convertToDto(request);
        boolean isRoleCreate = roleService.createRole(role);

        if (!isRoleCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

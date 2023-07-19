package it.academy.commands.role;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.RoleConverter;
import it.academy.dto.RoleDto;
import it.academy.service.IRoleService;
import it.academy.service.impl.RoleService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_ROLE;
import static it.academy.utils.constants.DataUI.ATTR_ROLE_ID;
import static it.academy.utils.constants.DataUI.EDIT_ROLE_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateRole implements Command {
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
        Integer roleId = Optional
                .ofNullable(request.getParameter(ATTR_ROLE_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        RoleDto role = roleService.findRoleById(roleId);

        request.setAttribute(ATTR_ROLE, role);
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_ROLE_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        RoleDto role = roleConverter.convertToDto(request);
        boolean isRoleUpdate = roleService.updateRole(role);

        if (!isRoleUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

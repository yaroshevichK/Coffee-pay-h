package it.academy.commands.user;

import it.academy.commands.Command;
import it.academy.dto.UserDto;
import it.academy.models.pageable.Pageable;
import it.academy.service.IUserService;
import it.academy.service.impl.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_USERNAME;
import static it.academy.utils.constants.DataUI.ATTR_USERNAME;
import static it.academy.utils.constants.DataUI.COMMAND_USERS;
import static it.academy.utils.constants.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.constants.DataUI.EMPTY_STRING;
import static it.academy.utils.constants.DataUI.FIRST_PAGE;
import static it.academy.utils.constants.DataUI.PAGEABLE;
import static it.academy.utils.constants.DataUI.PAGE_NUMBER;
import static it.academy.utils.constants.DataUI.PAGE_SIZE;
import static it.academy.utils.constants.DataUI.PARAM_PAGE_WITH_FILTER;
import static it.academy.utils.constants.DataUI.PARAM_SEARCH;
import static it.academy.utils.constants.DataUI.USERS_JSP;

public class GetUsers implements Command {
    private final IUserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageNumber = request.getParameter(PAGE_NUMBER);
        HashMap<String, Object> filterFields = getFilterFields(request);

        Pageable<UserDto> page = userService.getPageableRecords(
                Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(DEFAULT_PAGE_SIZE),
                Optional.ofNullable(pageNumber).map(Integer::parseInt).orElse(FIRST_PAGE),
                filterFields,
                null
        );

        setPageAttribute(request, page);
        return USERS_JSP;
    }

    private void setPageAttribute(HttpServletRequest request, Pageable<UserDto> page) {
        request.setAttribute(PAGEABLE, page);
        String searchFields = EMPTY_STRING;

        if (page.getSearchFields() != null) {
            String searchName = (String) page.getSearchFields().get(ATTR_USERNAME);

            if (StringUtils.isNotBlank(searchName)) {
                request.setAttribute(ATTR_SEARCH_USERNAME, searchName);
                searchFields = String.format(
                        PARAM_SEARCH,
                        ATTR_SEARCH_USERNAME,
                        searchName);
            }
        }

        String param = String.format(PARAM_PAGE_WITH_FILTER,
                COMMAND_USERS,
                searchFields);

        request.setAttribute(ATTR_PARAM_PAGE_URL, param);

    }

    private HashMap<String, Object> getFilterFields(HttpServletRequest request) {
        String searchName = request.getParameter(ATTR_SEARCH_USERNAME);

        HashMap<String, Object> filterFields = new HashMap<>();
        filterFields.put(ATTR_USERNAME, searchName);
        return filterFields;
    }
}

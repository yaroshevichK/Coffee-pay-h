package it.academy.commands.customer;

import it.academy.commands.Command;
import it.academy.dto.CustomerDto;
import it.academy.models.pageable.Pageable;
import it.academy.service.ICustomerService;
import it.academy.service.impl.CustomerService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_EMAIL;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_NAME;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_PHONE;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_SURNAME;
import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_CUSTOMER_EMAIL;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_CUSTOMER_NAME;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_CUSTOMER_PHONE;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_CUSTOMER_SURNAME;
import static it.academy.utils.constants.DataUI.COMMAND_CUSTOMERS;
import static it.academy.utils.constants.DataUI.CUSTOMERS_JSP;
import static it.academy.utils.constants.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.constants.DataUI.EMPTY_STRING;
import static it.academy.utils.constants.DataUI.FIRST_PAGE;
import static it.academy.utils.constants.DataUI.PAGEABLE;
import static it.academy.utils.constants.DataUI.PAGE_NUMBER;
import static it.academy.utils.constants.DataUI.PAGE_SIZE;
import static it.academy.utils.constants.DataUI.PARAM_PAGE_WITH_FILTER;
import static it.academy.utils.constants.DataUI.PARAM_SEARCH;

public class GetCustomers implements Command {
    private final ICustomerService customerService = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageNumber = request.getParameter(PAGE_NUMBER);
        HashMap<String, Object> filterFields = getFilterFields(request);

        Pageable<CustomerDto> page = customerService.getPageableRecords(
                Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(DEFAULT_PAGE_SIZE),
                Optional.ofNullable(pageNumber).map(Integer::parseInt).orElse(FIRST_PAGE),
                filterFields,
                null
        );

        setPageAttribute(request, page);
        return CUSTOMERS_JSP;
    }

    private void setPageAttribute(HttpServletRequest request, Pageable<CustomerDto> page) {
        request.setAttribute(PAGEABLE, page);

        String searchFields = Optional.ofNullable(page.getSearchFields())
                .orElse(new HashMap<>())
                .entrySet()
                .stream()
                .filter(pair -> (pair.getValue() != null && StringUtils.isNotBlank(pair.getValue().toString())))
                .map(pair -> {
                    if (ATTR_CUSTOMER_NAME.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_CUSTOMER_NAME, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_CUSTOMER_NAME, pair.getValue());
                    } else if (ATTR_CUSTOMER_SURNAME.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_CUSTOMER_SURNAME, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_CUSTOMER_SURNAME, pair.getValue());
                    } else if (ATTR_CUSTOMER_EMAIL.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_CUSTOMER_EMAIL, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_CUSTOMER_EMAIL, pair.getValue());
                    } else if (ATTR_CUSTOMER_PHONE.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_CUSTOMER_PHONE, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_CUSTOMER_PHONE, pair.getValue());
                    } else return EMPTY_STRING;

                })
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining());

        request.setAttribute(ATTR_PARAM_PAGE_URL, String.format(PARAM_PAGE_WITH_FILTER, COMMAND_CUSTOMERS,
                searchFields));

    }

    private HashMap<String, Object> getFilterFields(HttpServletRequest request) {
        String searchName = request.getParameter(ATTR_SEARCH_CUSTOMER_NAME);
        String searchSurname = request.getParameter(ATTR_SEARCH_CUSTOMER_SURNAME);
        String searchEmail = request.getParameter(ATTR_SEARCH_CUSTOMER_EMAIL);
        String searchPhone = request.getParameter(ATTR_SEARCH_CUSTOMER_PHONE);

        HashMap<String, Object> filterFields = new HashMap<>();
        filterFields.put(ATTR_CUSTOMER_NAME, searchName);
        filterFields.put(ATTR_CUSTOMER_SURNAME, searchSurname);
        filterFields.put(ATTR_CUSTOMER_EMAIL, searchEmail);
        filterFields.put(ATTR_CUSTOMER_PHONE, searchPhone);
        return filterFields;
    }
}

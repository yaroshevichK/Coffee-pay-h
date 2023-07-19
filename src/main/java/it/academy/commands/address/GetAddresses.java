package it.academy.commands.address;

import it.academy.commands.Command;
import it.academy.dto.AddressDto;
import it.academy.models.pageable.Pageable;
import it.academy.service.IAddressService;
import it.academy.service.impl.AddressService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import static it.academy.utils.constants.DataUI.ADDRESSES_JSP;
import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_CITY;
import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_STREET;
import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_ADDRESS_CITY;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_ADDRESS_STREET;
import static it.academy.utils.constants.DataUI.COMMAND_ADDRESSES;
import static it.academy.utils.constants.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.constants.DataUI.EMPTY_STRING;
import static it.academy.utils.constants.DataUI.FIRST_PAGE;
import static it.academy.utils.constants.DataUI.PAGEABLE;
import static it.academy.utils.constants.DataUI.PAGE_NUMBER;
import static it.academy.utils.constants.DataUI.PAGE_SIZE;
import static it.academy.utils.constants.DataUI.PARAM_PAGE_WITH_FILTER;
import static it.academy.utils.constants.DataUI.PARAM_SEARCH;

public class GetAddresses implements Command {
    private final IAddressService addressService = new AddressService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageNumber = request.getParameter(PAGE_NUMBER);
        HashMap<String, Object> filterFields = getFilterFields(request);

        Pageable<AddressDto> page = addressService.getPageableRecords(
                Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(DEFAULT_PAGE_SIZE),
                Optional.ofNullable(pageNumber).map(Integer::parseInt).orElse(FIRST_PAGE),
                filterFields,
                null
        );

        setPageAttribute(request, page);
        return ADDRESSES_JSP;
    }

    private void setPageAttribute(HttpServletRequest request, Pageable<AddressDto> page) {
        request.setAttribute(PAGEABLE, page);

        String searchFields = Optional.ofNullable(page.getSearchFields())
                .orElse(new HashMap<>())
                .entrySet()
                .stream()
                .filter(pair -> (pair.getValue() != null && StringUtils.isNotBlank(pair.getValue().toString())))
                .map(pair -> {
                    if (ATTR_ADDRESS_CITY.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_ADDRESS_CITY, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_ADDRESS_CITY, pair.getValue());
                    } else if (ATTR_ADDRESS_STREET.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_ADDRESS_STREET, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_ADDRESS_STREET, pair.getValue());
                    } else return EMPTY_STRING;

                })
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining());

        request.setAttribute(ATTR_PARAM_PAGE_URL,
                String.format(PARAM_PAGE_WITH_FILTER,
                        COMMAND_ADDRESSES,
                        searchFields));

    }

    private HashMap<String, Object> getFilterFields(HttpServletRequest request) {
        String searchCity = request.getParameter(ATTR_SEARCH_ADDRESS_CITY);
        String searchStreet = request.getParameter(ATTR_SEARCH_ADDRESS_STREET);

        HashMap<String, Object> filterFields = new HashMap<>();
        filterFields.put(ATTR_ADDRESS_CITY, searchCity);
        filterFields.put(ATTR_ADDRESS_STREET, searchStreet);
        return filterFields;
    }
}

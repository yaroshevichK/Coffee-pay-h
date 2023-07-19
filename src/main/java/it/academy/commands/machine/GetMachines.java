package it.academy.commands.machine;

import it.academy.commands.Command;
import it.academy.dto.MachineDto;
import it.academy.models.pageable.Pageable;
import it.academy.service.IMachineService;
import it.academy.service.impl.MachineService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import static it.academy.utils.constants.DataUI.ATTR_MACHINE_BRAND;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_CITY;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_NAME_MODEL;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_STREET;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_S_N;
import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_MACHINE_BRAND;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_MACHINE_CITY;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_MACHINE_NAME_MODEL;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_MACHINE_STREET;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_MACHINE_S_N;
import static it.academy.utils.constants.DataUI.COMMAND_MACHINES;
import static it.academy.utils.constants.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.constants.DataUI.EMPTY_STRING;
import static it.academy.utils.constants.DataUI.FIRST_PAGE;
import static it.academy.utils.constants.DataUI.MACHINES_JSP;
import static it.academy.utils.constants.DataUI.PAGEABLE;
import static it.academy.utils.constants.DataUI.PAGE_NUMBER;
import static it.academy.utils.constants.DataUI.PAGE_SIZE;
import static it.academy.utils.constants.DataUI.PARAM_PAGE_WITH_FILTER;
import static it.academy.utils.constants.DataUI.PARAM_SEARCH;

public class GetMachines implements Command {
    private final IMachineService machineService = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageNumber = request.getParameter(PAGE_NUMBER);
        HashMap<String, Object> filterFields = getFilterFields(request);

        Pageable<MachineDto> page = machineService.getPageableRecords(
                Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(DEFAULT_PAGE_SIZE),
                Optional.ofNullable(pageNumber).map(Integer::parseInt).orElse(FIRST_PAGE),
                filterFields,
                null
        );

        setPageAttribute(request, page);
        return MACHINES_JSP;
    }

    private void setPageAttribute(HttpServletRequest request, Pageable<MachineDto> page) {
        request.setAttribute(PAGEABLE, page);

        String searchFields = Optional.ofNullable(page.getSearchFields())
                .orElse(new HashMap<>())
                .entrySet()
                .stream()
                .filter(pair -> (pair.getValue() != null && StringUtils.isNotBlank(pair.getValue().toString())))
                .map(pair -> {
                    if (ATTR_MACHINE_S_N.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_MACHINE_S_N, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_MACHINE_S_N, pair.getValue());
                    } else if (ATTR_MACHINE_BRAND.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_MACHINE_BRAND, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_MACHINE_BRAND, pair.getValue());
                    } else if (ATTR_MACHINE_NAME_MODEL.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_MACHINE_NAME_MODEL, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_MACHINE_NAME_MODEL, pair.getValue());
                    } else if (ATTR_MACHINE_CITY.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_MACHINE_CITY, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_MACHINE_CITY, pair.getValue());
                    } else if (ATTR_MACHINE_STREET.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_MACHINE_STREET, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_MACHINE_STREET, pair.getValue());
                    } else return EMPTY_STRING;
                })
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining());

        request.setAttribute(ATTR_PARAM_PAGE_URL,
                String.format(PARAM_PAGE_WITH_FILTER, COMMAND_MACHINES, searchFields));

    }

    private HashMap<String, Object> getFilterFields(HttpServletRequest request) {
        String searchSerialNumber = request.getParameter(ATTR_SEARCH_MACHINE_S_N);
        String searchBrand = request.getParameter(ATTR_SEARCH_MACHINE_BRAND);
        String searchNameModel = request.getParameter(ATTR_SEARCH_MACHINE_NAME_MODEL);
        String searchCity = request.getParameter(ATTR_SEARCH_MACHINE_CITY);
        String searchStreet = request.getParameter(ATTR_SEARCH_MACHINE_STREET);

        HashMap<String, Object> filterFields = new HashMap<>();
        filterFields.put(ATTR_MACHINE_S_N, searchSerialNumber);
        filterFields.put(ATTR_MACHINE_BRAND, searchBrand);
        filterFields.put(ATTR_MACHINE_NAME_MODEL, searchNameModel);
        filterFields.put(ATTR_MACHINE_CITY, searchCity);
        filterFields.put(ATTR_MACHINE_STREET, searchStreet);
        return filterFields;
    }
}

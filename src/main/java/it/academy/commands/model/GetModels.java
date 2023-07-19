package it.academy.commands.model;

import it.academy.commands.Command;
import it.academy.dto.ModelDto;
import it.academy.models.pageable.Pageable;
import it.academy.service.IModelService;
import it.academy.service.impl.ModelService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import static it.academy.utils.constants.DataUI.ATTR_MODEL_BRAND;
import static it.academy.utils.constants.DataUI.ATTR_MODEL_NAME_MODEL;
import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_MODEL_BRAND;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_MODEL_NAME_MODEL;
import static it.academy.utils.constants.DataUI.COMMAND_MODELS;
import static it.academy.utils.constants.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.constants.DataUI.EMPTY_STRING;
import static it.academy.utils.constants.DataUI.FIRST_PAGE;
import static it.academy.utils.constants.DataUI.MODELS_JSP;
import static it.academy.utils.constants.DataUI.PAGEABLE;
import static it.academy.utils.constants.DataUI.PAGE_NUMBER;
import static it.academy.utils.constants.DataUI.PAGE_SIZE;
import static it.academy.utils.constants.DataUI.PARAM_PAGE_WITH_FILTER;
import static it.academy.utils.constants.DataUI.PARAM_SEARCH;

public class GetModels implements Command {
    private final IModelService modelService = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageNumber = request.getParameter(PAGE_NUMBER);
        HashMap<String, Object> filterFields = getFilterFields(request);

        Pageable<ModelDto> page = modelService.getPageableRecords(
                Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(DEFAULT_PAGE_SIZE),
                Optional.ofNullable(pageNumber).map(Integer::parseInt).orElse(FIRST_PAGE),
                filterFields,
                null
        );

        setPageAttribute(request, page);
        return MODELS_JSP;
    }

    private void setPageAttribute(HttpServletRequest request, Pageable<ModelDto> page) {
        request.setAttribute(PAGEABLE, page);

        String searchFields = Optional.ofNullable(page.getSearchFields())
                .orElse(new HashMap<>())
                .entrySet()
                .stream()
                .filter(pair -> (pair.getValue() != null && StringUtils.isNotBlank(pair.getValue().toString())))
                .map(pair -> {
                    if (ATTR_MODEL_BRAND.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_MODEL_BRAND, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_MODEL_BRAND, pair.getValue());
                    } else if (ATTR_MODEL_NAME_MODEL.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_MODEL_NAME_MODEL, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_MODEL_NAME_MODEL, pair.getValue());
                    } else return EMPTY_STRING;

                })
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining());

        request.setAttribute(ATTR_PARAM_PAGE_URL,
                String.format(PARAM_PAGE_WITH_FILTER,
                        COMMAND_MODELS,
                        searchFields));

    }

    private HashMap<String, Object> getFilterFields(HttpServletRequest request) {
        String searchBrand = request.getParameter(ATTR_SEARCH_MODEL_BRAND);
        String searchNameModel = request.getParameter(ATTR_SEARCH_MODEL_NAME_MODEL);

        HashMap<String, Object> filterFields = new HashMap<>();
        filterFields.put(ATTR_MODEL_BRAND, searchBrand);
        filterFields.put(ATTR_MODEL_NAME_MODEL, searchNameModel);
        return filterFields;
    }
}

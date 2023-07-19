package it.academy.commands.product;

import it.academy.commands.Command;
import it.academy.dto.ProductDto;
import it.academy.models.pageable.Pageable;
import it.academy.service.IProductService;
import it.academy.service.impl.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_NAME;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_PRICE;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_PRODUCT_NAME;
import static it.academy.utils.constants.DataUI.ATTR_SEARCH_PRODUCT_PRICE;
import static it.academy.utils.constants.DataUI.ATTR_SORT_FIELD;
import static it.academy.utils.constants.DataUI.COMMAND_PRODUCTS;
import static it.academy.utils.constants.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.constants.DataUI.EMPTY_STRING;
import static it.academy.utils.constants.DataUI.FIRST_PAGE;
import static it.academy.utils.constants.DataUI.PAGEABLE;
import static it.academy.utils.constants.DataUI.PAGE_NUMBER;
import static it.academy.utils.constants.DataUI.PAGE_SIZE;
import static it.academy.utils.constants.DataUI.PARAM_PAGE_WITH_SORT;
import static it.academy.utils.constants.DataUI.PARAM_SEARCH;
import static it.academy.utils.constants.DataUI.PARAM_SORT;
import static it.academy.utils.constants.DataUI.PRODUCTS_JSP;

public class GetProducts implements Command {
    private final IProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageNumber = request.getParameter(PAGE_NUMBER);
        String sortField = request.getParameter(ATTR_SORT_FIELD);
        HashMap<String, Object> filterFields = getFilterFields(request);

        Pageable<ProductDto> page = productService.getPageableRecords(
                Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(DEFAULT_PAGE_SIZE),
                Optional.ofNullable(pageNumber).map(Integer::parseInt).orElse(FIRST_PAGE),
                filterFields,
                sortField
        );

        setPageAttribute(request, page);
        return PRODUCTS_JSP;
    }

    private void setPageAttribute(HttpServletRequest request, Pageable<ProductDto> page) {
        request.setAttribute(PAGEABLE, page);

        String searchFields = Optional.ofNullable(page.getSearchFields())
                .orElse(new HashMap<>())
                .entrySet()
                .stream()
                .filter(pair -> (pair.getValue() != null && StringUtils.isNotBlank(pair.getValue().toString())))
                .map(pair -> {
                    if (ATTR_PRODUCT_NAME.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_PRODUCT_NAME, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_PRODUCT_NAME, pair.getValue());
                    } else if (ATTR_PRODUCT_PRICE.equals(pair.getKey())) {
                        request.setAttribute(ATTR_SEARCH_PRODUCT_PRICE, pair.getValue());
                        return String.format(PARAM_SEARCH, ATTR_SEARCH_PRODUCT_PRICE, pair.getValue());
                    } else return EMPTY_STRING;

                })
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining());

        request.setAttribute(ATTR_SORT_FIELD, page.getSortField());
        request.setAttribute(ATTR_PARAM_PAGE_URL,
                String.format(PARAM_PAGE_WITH_SORT,
                        COMMAND_PRODUCTS,
                        searchFields,
                        String.format(PARAM_SORT,
                                ATTR_SORT_FIELD,
                                page.getSortField())));

    }

    private HashMap<String, Object> getFilterFields(HttpServletRequest request) {
        String searchName = request.getParameter(ATTR_SEARCH_PRODUCT_NAME);
        String searchPrice = request.getParameter(ATTR_SEARCH_PRODUCT_PRICE);

        HashMap<String, Object> filterFields = new HashMap<>();
        filterFields.put(ATTR_PRODUCT_NAME, searchName);
        filterFields.put(ATTR_PRODUCT_PRICE, Optional
                .ofNullable(searchPrice)
                .map(price -> StringUtils.isNotBlank(price) ? Float.parseFloat(price) : null)
                .orElse(null));
        return filterFields;
    }
}

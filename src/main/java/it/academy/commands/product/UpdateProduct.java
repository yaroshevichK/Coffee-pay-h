package it.academy.commands.product;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.ProductConverter;
import it.academy.dto.ProductDto;
import it.academy.service.IProductService;
import it.academy.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.EDIT_PRODUCT_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateProduct implements Command {
    private final IProductService productService = new ProductService();
    private final IConverter<ProductDto> productConverter = new ProductConverter();

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
        Integer productId = Optional
                .ofNullable(request.getParameter(ATTR_PRODUCT_ID))
                .map(Integer::parseInt)
                .orElse(null);
        ProductDto product = productService.findProductById(productId);

        request.setAttribute(ATTR_PRODUCT, product);
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PRODUCT_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        ProductDto product = productConverter.convertToDto(request);
        boolean isProductUpdate = productService.updateProduct(product);

        if (!isProductUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

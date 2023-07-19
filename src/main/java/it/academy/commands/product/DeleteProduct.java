package it.academy.commands.product;

import it.academy.commands.Command;
import it.academy.service.IProductService;
import it.academy.service.impl.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class DeleteProduct implements Command {
    private final IProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer productId = Optional
                .ofNullable(request.getParameter(ATTR_PRODUCT_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        boolean isProductDelete = productService.deleteProductById(productId);

        if (!isProductDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

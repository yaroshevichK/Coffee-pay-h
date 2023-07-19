package it.academy.commands.discount;

import it.academy.commands.Command;
import it.academy.service.IDiscountService;
import it.academy.service.impl.DiscountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class DeleteDiscount implements Command {
    private final IDiscountService discountService = new DiscountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer discountId = Optional
                .ofNullable(request.getParameter(ATTR_DISCOUNT_ID))
                .map(Integer::parseInt)
                .orElse(null);

        boolean isDiscountDelete = discountService.deleteDiscountById(discountId);

        if (!isDiscountDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

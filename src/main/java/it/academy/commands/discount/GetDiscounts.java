package it.academy.commands.discount;

import it.academy.commands.Command;
import it.academy.service.IDiscountService;
import it.academy.service.impl.DiscountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.DataUI.ATTR_LIST_DISCOUNTS;
import static it.academy.utils.constants.DataUI.DISCOUNTS_JSP;

public class GetDiscounts implements Command {
    private final IDiscountService discountService = new DiscountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ATTR_LIST_DISCOUNTS, discountService.findAllDiscounts());
        return DISCOUNTS_JSP;
    }
}

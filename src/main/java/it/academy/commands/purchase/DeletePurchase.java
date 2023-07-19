package it.academy.commands.purchase;

import it.academy.commands.Command;
import it.academy.service.IPurchaseService;
import it.academy.service.impl.PurchaseService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.ATTR_PURCHASE_ID;
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class DeletePurchase implements Command {
    private final IPurchaseService purchaseService = new PurchaseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer purchaseId = Optional
                .ofNullable(request.getParameter(ATTR_PURCHASE_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        boolean isPurchaseDelete = purchaseService.deletePurchaseById(purchaseId);

        if (!isPurchaseDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

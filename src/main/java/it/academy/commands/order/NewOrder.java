package it.academy.commands.order;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.PurchaseConverter;
import it.academy.dto.PurchaseDto;
import it.academy.service.IPurchaseService;
import it.academy.service.impl.PurchaseService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_ID;
import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT_ID;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT_ID;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.MAIN_ORDER_JSP;

public class NewOrder implements Command {
    private final IPurchaseService purchaseService = new PurchaseService();
    private final IConverter<PurchaseDto> purchaseConverter = new PurchaseConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer customerId = getId(request.getParameter(ATTR_CUSTOMER_ID));
        Integer machineId = getId(request.getParameter(ATTR_MACHINE_ID));
        Integer productId = getId(request.getParameter(ATTR_PRODUCT_ID));
        Integer discountId = getId(request.getParameter(ATTR_DISCOUNT_ID));
        Integer creditCardId = getId(request.getParameter(ATTR_CREDIT_CARD_ID));
        Integer typePaymentId = getId(request.getParameter(ATTR_TYPE_PAYMENT_ID));

        PurchaseDto purchaseDto = purchaseConverter.convertToDto(request);
        boolean isPurchaseCreate = purchaseService.createPurchase(purchaseDto,
                customerId,
                discountId,
                machineId,
                productId,
                creditCardId,
                typePaymentId);

        if (!isPurchaseCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return MAIN_ORDER_JSP;
        }
    }

    private Integer getId(String param) {
        return Optional
                .ofNullable(param)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt).orElse(null);
    }
}

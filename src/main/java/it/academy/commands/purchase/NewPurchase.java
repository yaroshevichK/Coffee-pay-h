package it.academy.commands.purchase;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.PurchaseConverter;
import it.academy.dto.PurchaseDto;
import it.academy.service.ICreditCardService;
import it.academy.service.ICustomerService;
import it.academy.service.IDiscountService;
import it.academy.service.IMachineService;
import it.academy.service.IProductService;
import it.academy.service.IPurchaseService;
import it.academy.service.ITypePaymentService;
import it.academy.service.impl.CreditCardService;
import it.academy.service.impl.CustomerService;
import it.academy.service.impl.DiscountService;
import it.academy.service.impl.MachineService;
import it.academy.service.impl.ProductService;
import it.academy.service.impl.PurchaseService;
import it.academy.service.impl.TypePaymentService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMERS;
import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER_ID;
import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT_ID;
import static it.academy.utils.constants.DataUI.ATTR_LIST_CREDIT_CARDS;
import static it.academy.utils.constants.DataUI.ATTR_LIST_DISCOUNTS;
import static it.academy.utils.constants.DataUI.ATTR_LIST_PRODUCTS;
import static it.academy.utils.constants.DataUI.ATTR_LIST_TYPE_PAYMENTS;
import static it.academy.utils.constants.DataUI.ATTR_MACHINES;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.ATTR_TYPE_PAYMENT_ID;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.NEW_PURCHASE_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class NewPurchase implements Command {
    private final IPurchaseService purchaseService = new PurchaseService();
    private final ICustomerService customerService = new CustomerService();
    private final IMachineService machineService = new MachineService();
    private final IProductService productService = new ProductService();
    private final IDiscountService discountService = new DiscountService();
    private final ICreditCardService creditCardService = new CreditCardService();
    private final ITypePaymentService typePaymentService = new TypePaymentService();
    private final IConverter<PurchaseDto> purchaseConverter = new PurchaseConverter();

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
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        request.setAttribute(ATTR_CUSTOMERS, customerService.findAllCustomers());
        request.setAttribute(ATTR_MACHINES, machineService.findAllMachines());
        request.setAttribute(ATTR_LIST_PRODUCTS, productService.findAllProducts());
        request.setAttribute(ATTR_LIST_DISCOUNTS, discountService.findAllDiscounts());
        request.setAttribute(ATTR_LIST_CREDIT_CARDS, creditCardService.findAllCreditCards());
        request.setAttribute(ATTR_LIST_TYPE_PAYMENTS, typePaymentService.findAllTypePayments());

        return NEW_PURCHASE_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);
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
            return prevUrl;
        }
    }

    private Integer getId(String param) {
        return Optional
                .ofNullable(param)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt).orElse(null);
    }
}

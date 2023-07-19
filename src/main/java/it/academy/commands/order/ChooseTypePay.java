package it.academy.commands.order;

import it.academy.commands.Command;
import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.dto.DiscountDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.dto.TypePaymentDto;
import it.academy.dto.UserDto;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_CUSTOMER;
import static it.academy.utils.constants.DataUI.ATTR_DISCOUNT;
import static it.academy.utils.constants.DataUI.ATTR_LIST_CREDIT_CARDS;
import static it.academy.utils.constants.DataUI.ATTR_LIST_TYPE_PAYMENTS;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.ATTR_PURCHASE_AMOUNT;
import static it.academy.utils.constants.DataUI.LOGGED_USER;
import static it.academy.utils.constants.DataUI.ORDER_TYPE_PAY_JSP;

public class ChooseTypePay implements Command {
    private final ICustomerService customerService = new CustomerService();
    private final IMachineService machineService = new MachineService();
    private final IProductService productService = new ProductService();
    private final ITypePaymentService typePaymentService = new TypePaymentService();
    private final ICreditCardService creditCardService = new CreditCardService();
    private final IDiscountService discountService = new DiscountService();
    private final IPurchaseService purchaseService = new PurchaseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(LOGGED_USER);

        Integer machineId = Optional
                .ofNullable(request.getParameter(ATTR_MACHINE_ID))
                .map(Integer::parseInt)
                .orElse(null);
        Integer productId = Optional
                .ofNullable(request.getParameter(ATTR_PRODUCT_ID))
                .map(Integer::parseInt)
                .orElse(null);

        CustomerDto customer = Optional.ofNullable(userDto)
                .map(UserDto::getUsername)
                .map(customerService::findCustomerByUsername)
                .orElse(null);

        MachineDto machine = machineService.findMachineById(machineId);
        ProductDto product = productService.findProductById(productId);
        List<TypePaymentDto> typePayments = typePaymentService.findAllTypePayments();

        List<CreditCardDto> creditCards = Optional.ofNullable(userDto)
                .map(UserDto::getUsername)
                .map(creditCardService::findCreditCardByUsername)
                .orElse(null);

        Float amountOrders = Optional.ofNullable(userDto)
                .map(UserDto::getUsername)
                .map(purchaseService::getSumPurchases)
                .orElse(null);

        DiscountDto discount = Optional.ofNullable(amountOrders)
                .map(discountService::getPercentDiscount)
                .orElse(null);

        Float amount = Optional.ofNullable(product)
                .map(productDto -> getSumOrder(discount, productDto)).orElse(0F);

        request.setAttribute(ATTR_CUSTOMER, customer);
        request.setAttribute(ATTR_PRODUCT, product);
        request.setAttribute(ATTR_MACHINE, machine);
        request.setAttribute(ATTR_LIST_TYPE_PAYMENTS, typePayments);
        request.setAttribute(ATTR_LIST_CREDIT_CARDS, creditCards);
        request.setAttribute(ATTR_DISCOUNT, discount);
        request.setAttribute(ATTR_PURCHASE_AMOUNT, amount);

        return ORDER_TYPE_PAY_JSP;
    }

    private Float getSumOrder(DiscountDto discountDto, ProductDto productDto) {
        Float percent = Optional.ofNullable(discountDto)
                .map(DiscountDto::getPercent)
                .orElse(0f);
        float sumDiscount = productDto.getPrice() / 100 * percent;
        return (float) (Math.ceil(((productDto.getPrice() - sumDiscount) * 100)) / 100);
    }
}

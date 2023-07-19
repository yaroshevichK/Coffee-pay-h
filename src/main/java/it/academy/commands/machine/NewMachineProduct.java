package it.academy.commands.machine;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.MachineConverter;
import it.academy.dto.MachineDto;
import it.academy.service.IAddressService;
import it.academy.service.IMachineService;
import it.academy.service.IModelService;
import it.academy.service.IProductService;
import it.academy.service.impl.AddressService;
import it.academy.service.impl.MachineService;
import it.academy.service.impl.ModelService;
import it.academy.service.impl.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_ADDRESSES;
import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_ID;
import static it.academy.utils.constants.DataUI.ATTR_LIST_PRODUCTS;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_MODELS;
import static it.academy.utils.constants.DataUI.ATTR_MODEL_ID;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.NEW_MACHINE_JSP;
import static it.academy.utils.constants.DataUI.NEW_MACHINE_PRODUCT_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;
import static it.academy.utils.constants.DataUI.PREV_URL_MACHINES;

public class NewMachineProduct implements Command {
    private final IMachineService machineService = new MachineService();
    private final IProductService productService = new ProductService();
    private final IConverter<MachineDto> machineConverter = new MachineConverter();

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
        request.setAttribute(PREV_URL_MACHINES, request.getParameter(PREV_URL_MACHINES));
        request.setAttribute(ATTR_MACHINE_ID, request.getParameter(ATTR_MACHINE_ID));
        request.setAttribute(ATTR_LIST_PRODUCTS, productService.findAllProducts());

        return NEW_MACHINE_PRODUCT_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevMachineUrl = request.getParameter(PREV_URL_MACHINES);
        String prevUrl = request.getParameter(PREV_URL);

        Integer machineId = Optional
                .ofNullable(request.getParameter(ATTR_MACHINE_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        Integer productId = Optional
                .ofNullable(request.getParameter(ATTR_PRODUCT_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        boolean isMachineProductCreate = machineService.createMachineProduct(machineId,productId);

        request.setAttribute(ATTR_MACHINE_ID,machineId);
        request.setAttribute(PREV_URL_MACHINES,prevMachineUrl);

        if (!isMachineProductCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

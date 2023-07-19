package it.academy.commands.order;

import it.academy.commands.Command;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.service.IMachineService;
import it.academy.service.IProductService;
import it.academy.service.impl.MachineService;
import it.academy.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_LIST_MACHINES;
import static it.academy.utils.constants.DataUI.ATTR_LIST_PRODUCTS;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.COMMAND_MACHINE_PRODUCTS;
import static it.academy.utils.constants.DataUI.ORDER_MACHINES_JSP;
import static it.academy.utils.constants.DataUI.ORDER_PRODUCTS_JSP;
import static it.academy.utils.constants.DataUI.PARAM_PAGE;

public class ChooseProduct implements Command {
    private final IMachineService machineService = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer machineId = Optional
                .ofNullable(request.getParameter(ATTR_MACHINE_ID))
                .map(Integer::parseInt)
                .orElse(null);

        MachineDto machine = machineService.findMachineById(machineId);
        List<ProductDto> productList = new ArrayList<>(machine.getProducts());

        request.setAttribute(ATTR_LIST_PRODUCTS, productList);
        request.setAttribute(ATTR_MACHINE, machine);

        return ORDER_PRODUCTS_JSP;
    }
}

package it.academy.commands.machine;

import it.academy.commands.Command;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.service.IMachineService;
import it.academy.service.impl.MachineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_LIST_PRODUCTS;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.COMMAND_MACHINE_PRODUCTS;
import static it.academy.utils.constants.DataUI.MACHINE_PRODUCTS_JSP;
import static it.academy.utils.constants.DataUI.PARAM_PAGE;
import static it.academy.utils.constants.DataUI.PREV_URL_MACHINES;

public class GetMachineProducts implements Command {
    private final IMachineService machineService = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrlMachines = request.getParameter(PREV_URL_MACHINES);
        Integer machineId = Optional
                .ofNullable(request.getParameter(ATTR_MACHINE_ID))
                .map(Integer::parseInt)
                .orElse(null);

        MachineDto machine = machineService.findMachineById(machineId);
        List<ProductDto> productList = new ArrayList<>(machine.getProducts());

        request.setAttribute(ATTR_LIST_PRODUCTS, productList);
        request.setAttribute(ATTR_PARAM_PAGE_URL,
                String.format(PARAM_PAGE, COMMAND_MACHINE_PRODUCTS));
        request.setAttribute(ATTR_MACHINE, machine);
        request.setAttribute(PREV_URL_MACHINES, prevUrlMachines);

        return MACHINE_PRODUCTS_JSP;
    }
}

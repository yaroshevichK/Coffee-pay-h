package it.academy.commands.machine;

import it.academy.commands.Command;
import it.academy.service.IMachineService;
import it.academy.service.impl.MachineService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_PRODUCT_ID;
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;
import static it.academy.utils.constants.DataUI.PREV_URL_MACHINES;

public class DeleteMachineProduct implements Command {
    private final IMachineService machineService = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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

        boolean isMachineProductDelete = machineService.deleteMachineProduct(machineId, productId);

        request.setAttribute(ATTR_MACHINE_ID, machineId);
        request.setAttribute(PREV_URL_MACHINES, prevMachineUrl);

        if (!isMachineProductDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

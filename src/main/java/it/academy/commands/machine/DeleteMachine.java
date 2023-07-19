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
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class DeleteMachine implements Command {
    private final IMachineService machineService = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer machineId = Optional
                .ofNullable(request.getParameter(ATTR_MACHINE_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        boolean isMachineDelete = machineService.deleteMachineById(machineId);

        if (!isMachineDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

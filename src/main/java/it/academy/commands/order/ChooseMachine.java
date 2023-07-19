package it.academy.commands.order;

import it.academy.commands.Command;
import it.academy.service.IMachineService;
import it.academy.service.impl.MachineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.DataUI.ATTR_LIST_MACHINES;
import static it.academy.utils.constants.DataUI.ORDER_MACHINES_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class ChooseMachine implements Command {
    private final IMachineService machineService = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ATTR_LIST_MACHINES, machineService.findAllMachines());
        return ORDER_MACHINES_JSP;
    }
}

package it.academy.commands.machine;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.MachineConverter;
import it.academy.dto.MachineDto;
import it.academy.service.IAddressService;
import it.academy.service.IMachineService;
import it.academy.service.IModelService;
import it.academy.service.impl.AddressService;
import it.academy.service.impl.MachineService;
import it.academy.service.impl.ModelService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_ADDRESSES;
import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_ID;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_MODELS;
import static it.academy.utils.constants.DataUI.ATTR_MODEL_ID;
import static it.academy.utils.constants.DataUI.EDIT_MACHINE_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateMachine implements Command {
    private final IMachineService machineService = new MachineService();
    private final IModelService modelService = new ModelService();
    private final IAddressService addressService = new AddressService();
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
        Integer machineId = Optional
                .ofNullable(request.getParameter(ATTR_MACHINE_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        MachineDto machineDto = machineService.findMachineById(machineId);

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        request.setAttribute(ATTR_MACHINE, machineDto);
        request.setAttribute(ATTR_MODELS, modelService.findAllModels());
        request.setAttribute(ATTR_ADDRESSES, addressService.findAllAddresses());
        return EDIT_MACHINE_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer modelId = Optional
                .ofNullable(request.getParameter(ATTR_MODEL_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        Integer addressId = Optional
                .ofNullable(request.getParameter(ATTR_ADDRESS_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);

        MachineDto machine = machineConverter.convertToDto(request);
        boolean isMachineUpdate = machineService.updateMachine(machine, addressId, modelId);

        if (!isMachineUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

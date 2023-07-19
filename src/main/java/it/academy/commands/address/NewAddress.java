package it.academy.commands.address;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.AddressConverter;
import it.academy.dto.AddressDto;
import it.academy.service.IAddressService;
import it.academy.service.impl.AddressService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_NEW;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.NEW_ADDRESS_JSP;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class NewAddress implements Command {
    private final IAddressService addressService = new AddressService();
    private final IConverter<AddressDto> addressConverter = new AddressConverter();

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
        return NEW_ADDRESS_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        AddressDto address = addressConverter.convertToDto(request);
        boolean isAddressCreate = addressService.createAddress(address);

        if (!isAddressCreate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_NEW);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

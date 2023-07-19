package it.academy.commands.address;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.AddressConverter;
import it.academy.dto.AddressDto;
import it.academy.service.IAddressService;
import it.academy.service.impl.AddressService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_ADDRESS;
import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.EDIT_ADDRESS_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateAddress implements Command {
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
        Integer addressId = Optional
                .ofNullable(request.getParameter(ATTR_ADDRESS_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        AddressDto address = addressService.findAddressById(addressId);

        request.setAttribute(ATTR_ADDRESS, address);
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_ADDRESS_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        AddressDto address = addressConverter.convertToDto(request);
        boolean isAddressUpdate = addressService.updateAddress(address);

        if (!isAddressUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

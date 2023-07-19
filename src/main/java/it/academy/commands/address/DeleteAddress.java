package it.academy.commands.address;

import it.academy.commands.Command;
import it.academy.service.IAddressService;
import it.academy.service.impl.AddressService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_ADDRESS_ID;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ERROR_DELETE;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class DeleteAddress implements Command {
    private final IAddressService addressService = new AddressService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        Integer addressId = Optional
                .ofNullable(request.getParameter(ATTR_ADDRESS_ID))
                .map(Integer::parseInt)
                .orElse(null);

        boolean isAddressDelete = addressService.deleteAddressById(addressId);

        if (!isAddressDelete) {
            request.setAttribute(ATTR_MESSAGE, ERROR_DELETE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

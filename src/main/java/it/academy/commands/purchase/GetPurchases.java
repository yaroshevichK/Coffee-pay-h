package it.academy.commands.purchase;

import it.academy.commands.Command;
import it.academy.dto.PurchaseDto;
import it.academy.models.pageable.Pageable;
import it.academy.service.IPurchaseService;
import it.academy.service.impl.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_PARAM_PAGE_URL;
import static it.academy.utils.constants.DataUI.COMMAND_PURCHASES;
import static it.academy.utils.constants.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.constants.DataUI.FIRST_PAGE;
import static it.academy.utils.constants.DataUI.PAGEABLE;
import static it.academy.utils.constants.DataUI.PAGE_NUMBER;
import static it.academy.utils.constants.DataUI.PAGE_SIZE;
import static it.academy.utils.constants.DataUI.PARAM_PAGE;
import static it.academy.utils.constants.DataUI.PURCHASES_JSP;

public class GetPurchases implements Command {
    private final IPurchaseService machineService = new PurchaseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageNumber = request.getParameter(PAGE_NUMBER);

        Pageable<PurchaseDto> page = machineService.getPageableRecords(
                Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(DEFAULT_PAGE_SIZE),
                Optional.ofNullable(pageNumber).map(Integer::parseInt).orElse(FIRST_PAGE),
                null,
                null
        );

        setPageAttribute(request, page);
        return PURCHASES_JSP;
    }

    private void setPageAttribute(HttpServletRequest request, Pageable<PurchaseDto> page) {
        request.setAttribute(PAGEABLE, page);
        request.setAttribute(ATTR_PARAM_PAGE_URL,
                String.format(PARAM_PAGE, COMMAND_PURCHASES));
    }
}

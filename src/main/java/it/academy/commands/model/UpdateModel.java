package it.academy.commands.model;

import it.academy.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.ModelConverter;
import it.academy.dto.ModelDto;
import it.academy.service.IModelService;
import it.academy.service.impl.ModelService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.ATTR_MODEL;
import static it.academy.utils.constants.DataUI.ATTR_MODEL_ID;
import static it.academy.utils.constants.DataUI.EDIT_MODEL_JSP;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.ERROR_REQUEST;
import static it.academy.utils.constants.DataUI.ERROR_UPDATE;
import static it.academy.utils.constants.DataUI.GET_REQUEST;
import static it.academy.utils.constants.DataUI.POST_REQUEST;
import static it.academy.utils.constants.DataUI.PREV_URL;

public class UpdateModel implements Command {
    private final IModelService modelService = new ModelService();
    private final IConverter<ModelDto> modelConverter = new ModelConverter();

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
        Integer modelId = Optional
                .ofNullable(request.getParameter(ATTR_MODEL_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        ModelDto model = modelService.findModelById(modelId);

        request.setAttribute(ATTR_MODEL, model);
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_MODEL_JSP;
    }

    private String doPost(HttpServletRequest request) {
        String prevUrl = request.getParameter(PREV_URL);

        ModelDto model = modelConverter.convertToDto(request);
        boolean isModelUpdate = modelService.updateModel(model);

        if (!isModelUpdate) {
            request.setAttribute(ATTR_MESSAGE, ERROR_UPDATE);
            return ERROR_JSP;
        } else {
            return prevUrl;
        }
    }
}

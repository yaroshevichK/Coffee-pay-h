package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.dto.MachineDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ID;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_S_N;


public class MachineConverter implements IConverter<MachineDto> {
    @Override
    public MachineDto convertToDto(HttpServletRequest request) {
        Integer id = Optional
                .ofNullable(request.getParameter(ATTR_MACHINE_ID))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        String serialNumber = request.getParameter(ATTR_MACHINE_S_N);

        return MachineDto.builder()
                .id(id)
                .serialNumber(serialNumber)
                .build();
    }
}

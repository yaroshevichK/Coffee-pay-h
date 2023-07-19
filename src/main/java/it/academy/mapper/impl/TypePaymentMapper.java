package it.academy.mapper.impl;

import it.academy.dto.TypePaymentDto;
import it.academy.mapper.IMapper;
import it.academy.models.TypePayment;

public class TypePaymentMapper implements IMapper<TypePayment, TypePaymentDto> {
    @Override
    public TypePayment dtoToEntity(TypePaymentDto typePaymentDto) {
        return TypePayment.builder()
                .id(typePaymentDto.getId())
                .name(typePaymentDto.getName())
                .useCreditCard(typePaymentDto.getUseCreditCard())
                .usePhoneNumber(typePaymentDto.getUsePhoneNumber())
                .build();
    }

    @Override
    public TypePaymentDto entityToDto(TypePayment typePayment) {
        return TypePaymentDto.builder()
                .id(typePayment.getId())
                .name(typePayment.getName())
                .useCreditCard(typePayment.getUseCreditCard())
                .usePhoneNumber(typePayment.getUsePhoneNumber())
                .build();
    }
}

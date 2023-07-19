package it.academy.mapper.impl;

import it.academy.dto.DiscountDto;
import it.academy.mapper.IMapper;
import it.academy.models.Discount;

public class DiscountMapper implements IMapper<Discount, DiscountDto> {
    @Override
    public Discount dtoToEntity(DiscountDto discountDto) {
        return Discount.builder()
                .id(discountDto.getId())
                .percent(discountDto.getPercent())
                .amount(discountDto.getAmount())
                .build();
    }

    @Override
    public DiscountDto entityToDto(Discount discount) {
        return DiscountDto.builder()
                .id(discount.getId())
                .percent(discount.getPercent())
                .amount(discount.getAmount())
                .build();
    }
}

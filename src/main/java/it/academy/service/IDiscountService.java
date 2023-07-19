package it.academy.service;

import it.academy.dto.DiscountDto;

import java.util.List;

public interface IDiscountService {
    boolean createDiscount(DiscountDto discountDto);

    DiscountDto findDiscountById(Integer id);

    boolean updateDiscount(DiscountDto discountDto);

    boolean deleteDiscountById(Integer id);

    List<DiscountDto> findAllDiscounts();

    DiscountDto getPercentDiscount(Float amount);
}

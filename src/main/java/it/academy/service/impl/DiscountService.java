package it.academy.service.impl;

import it.academy.dto.DiscountDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.DiscountMapper;
import it.academy.models.Discount;
import it.academy.repositories.IDiscountRepository;
import it.academy.repositories.impl.DiscountRepository;
import it.academy.service.IDiscountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiscountService implements IDiscountService {
    private final IDiscountRepository discountRepository = new DiscountRepository();
    private final IMapper<Discount, DiscountDto> discountMapper = new DiscountMapper();

    @Override
    public boolean createDiscount(DiscountDto discountDto) {
        Discount discount = discountMapper.dtoToEntity(discountDto);
        return discountRepository.save(discount);
    }

    @Override
    public DiscountDto findDiscountById(Integer id) {
        Discount discount = discountRepository.findById(id);
        return discountMapper.entityToDto(discount);
    }

    @Override
    public boolean updateDiscount(DiscountDto discountDto) {
        Discount discount = discountMapper.dtoToEntity(discountDto);
        return discountRepository.update(discount);
    }

    @Override
    public boolean deleteDiscountById(Integer id) {
        return discountRepository.delete(id);
    }

    @Override
    public List<DiscountDto> findAllDiscounts() {
        List<Discount> discountList = discountRepository.findAll();
        return Optional.ofNullable(discountList)
                .orElse(new ArrayList<>())
                .stream()
                .map(discountMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiscountDto getPercentDiscount(Float amount) {
        Discount discount = discountRepository.getPercentDiscount(amount);
        return Optional.ofNullable(discount)
                .map(discountMapper::entityToDto)
                .orElse(null);
    }
}

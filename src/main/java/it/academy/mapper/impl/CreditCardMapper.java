package it.academy.mapper.impl;

import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.mapper.IMapper;
import it.academy.models.CreditCard;
import it.academy.models.Customer;

import java.util.Optional;

public class CreditCardMapper implements IMapper<CreditCard, CreditCardDto> {
    IMapper<Customer, CustomerDto> customerMapper = new CustomerMapper();

    @Override
    public CreditCard dtoToEntity(CreditCardDto creditCardDto) {
        Customer customer = Optional.ofNullable(creditCardDto.getCustomer())
                .map(customerMapper::dtoToEntity)
                .orElse(null);

        return CreditCard.builder()
                .id(creditCardDto.getId())
                .number(creditCardDto.getNumber())
                .customer(customer)
                .build();
    }

    @Override
    public CreditCardDto entityToDto(CreditCard creditCard) {
        CustomerDto customerDto = Optional.ofNullable(creditCard.getCustomer())
                .map(customerMapper::entityToDto)
                .orElse(null);

        return CreditCardDto.builder()
                .id(creditCard.getId())
                .number(creditCard.getNumber())
                .customer(customerDto)
                .build();
    }
}

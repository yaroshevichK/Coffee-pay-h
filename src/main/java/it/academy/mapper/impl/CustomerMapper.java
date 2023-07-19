package it.academy.mapper.impl;

import it.academy.dto.CustomerDto;
import it.academy.mapper.IMapper;
import it.academy.models.Customer;

public class CustomerMapper implements IMapper<Customer, CustomerDto> {
    @Override
    public Customer dtoToEntity(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .phone(customerDto.getPhone())
                .email(customerDto.getEmail())
                .build();
    }

    @Override
    public CustomerDto entityToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .build();
    }
}

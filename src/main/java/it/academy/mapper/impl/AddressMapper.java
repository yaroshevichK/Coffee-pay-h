package it.academy.mapper.impl;

import it.academy.dto.AddressDto;
import it.academy.mapper.IMapper;
import it.academy.models.Address;

public class AddressMapper implements IMapper<Address, AddressDto> {
    @Override
    public Address dtoToEntity(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getId())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .build();
    }

    @Override
    public AddressDto entityToDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }
}

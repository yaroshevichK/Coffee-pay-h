package it.academy.service.impl;

import it.academy.dto.AddressDto;
import it.academy.dto.RoleDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.AddressMapper;
import it.academy.mapper.impl.PageableMapper;
import it.academy.models.Address;
import it.academy.models.Role;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IAddressRepository;
import it.academy.repositories.impl.AddressRepository;
import it.academy.service.IAddressService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressService implements IAddressService {
    private final IAddressRepository addressRepository = new AddressRepository();

    private final IMapper<Address, AddressDto> addressMapper = new AddressMapper();
    private final IMapper<Pageable<Address>, Pageable<AddressDto>> pageableMapper
            = new PageableMapper<>(addressMapper);

    @Override
    public Pageable<AddressDto> getPageableRecords(Integer pageSize,
                                                Integer pageNumber,
                                                HashMap<String, Object> searchFields,
                                                String sortField) {
        Pageable<Address> pageable = addressRepository
                .getPageableRecords(pageSize,
                        pageNumber,
                        searchFields,
                        sortField);

        return pageableMapper.entityToDto(pageable);
    }

    @Override
    public boolean createAddress(AddressDto addressDto) {
        Address address = addressMapper.dtoToEntity(addressDto);
        return addressRepository.save(address);
    }

    @Override
    public AddressDto findAddressById(Integer id) {
        Address address = addressRepository.findById(id);
        return addressMapper.entityToDto(address);
    }

    @Override
    public boolean updateAddress(AddressDto addressDto) {
        Address address = addressMapper.dtoToEntity(addressDto);
        return addressRepository.update(address);
    }

    @Override
    public boolean deleteAddressById(Integer id) {
        return addressRepository.delete(id);
    }

    @Override
    public List<AddressDto> findAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        return Optional.ofNullable(addressList)
                .orElse(new ArrayList<>())
                .stream()
                .map(addressMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

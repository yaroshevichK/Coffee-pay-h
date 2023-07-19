package it.academy.service;

import it.academy.dto.AddressDto;
import it.academy.dto.RoleDto;
import it.academy.models.pageable.Pageable;

import java.util.HashMap;
import java.util.List;

public interface IAddressService {
    Pageable<AddressDto> getPageableRecords(Integer pageSize,
                                            Integer pageNumber,
                                            HashMap<String, Object> searchFields,
                                            String sortField);

    boolean createAddress(AddressDto addressDto);

    AddressDto findAddressById(Integer id);

    boolean updateAddress(AddressDto addressDto);

    boolean deleteAddressById(Integer id);

    List<AddressDto> findAllAddresses();
}

package it.academy.service;

import it.academy.dto.CustomerDto;
import it.academy.models.pageable.Pageable;

import java.util.HashMap;
import java.util.List;

public interface ICustomerService {
    boolean createCustomer(String username, String password, CustomerDto customerDto);

    CustomerDto findCustomerByUsername(String username);

    boolean updateCustomer(String username, CustomerDto customerDto);

    CustomerDto findCustomerById(Integer id);

    boolean deleteCustomerById(Integer id);

    Pageable<CustomerDto> getPageableRecords(Integer pageSize,
                                             Integer pageNumber,
                                             HashMap<String, Object> searchFields,
                                             String sortField);

    boolean updateCustomer(CustomerDto customerDto);
    List<CustomerDto> findAllCustomers();
}


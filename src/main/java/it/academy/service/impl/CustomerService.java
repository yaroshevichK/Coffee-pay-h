package it.academy.service.impl;

import it.academy.dto.CustomerDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.CustomerMapper;
import it.academy.mapper.impl.PageableMapper;
import it.academy.models.Customer;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.impl.CustomerRepository;
import it.academy.service.ICustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository = new CustomerRepository();
    private final IMapper<Customer, CustomerDto> customerMapper = new CustomerMapper();
    private final IMapper<Pageable<Customer>, Pageable<CustomerDto>> pageableMapper
            = new PageableMapper<>(customerMapper);

    @Override
    public boolean createCustomer(String username, String password, CustomerDto customerDto) {
        Customer customer = customerMapper.dtoToEntity(customerDto);
        return customerRepository.createCustomer(username, password, customer);
    }

    @Override
    public CustomerDto findCustomerByUsername(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        return Optional.ofNullable(customer)
                .map(customerMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public boolean updateCustomer(String username, CustomerDto customerDto) {
        Customer customer = customerMapper.dtoToEntity(customerDto);
        return customerRepository.updateCustomer(username, customer);
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.dtoToEntity(customerDto);
        return customerRepository.updateCustomer(customer);
    }

    @Override
    public CustomerDto findCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id);
        return Optional.ofNullable(customer)
                .map(customerMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public boolean deleteCustomerById(Integer id) {
        return customerRepository.delete(id);
    }

    @Override
    public Pageable<CustomerDto> getPageableRecords(Integer pageSize,
                                                    Integer pageNumber,
                                                    HashMap<String, Object> searchFields,
                                                    String sortField) {
        Pageable<Customer> pageable = customerRepository
                .getPageableRecords(pageSize,
                        pageNumber,
                        searchFields,
                        sortField);

        return pageableMapper.entityToDto(pageable);
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return Optional.ofNullable(customers)
                .orElse(new ArrayList<>())
                .stream()
                .map(customerMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

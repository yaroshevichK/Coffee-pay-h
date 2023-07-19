package it.academy.repositories;

import it.academy.models.Customer;

public interface ICustomerRepository extends ICrudRepository<Customer> {
    boolean createCustomer(String username, String password, Customer customer);

    Customer findCustomerByUsername(String username);

    boolean updateCustomer(String username, Customer customer);

    boolean updateCustomer(Customer customer);
}

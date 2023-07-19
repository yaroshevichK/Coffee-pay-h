package it.academy.repositories;

import it.academy.models.Product;

import java.util.List;

public interface IProductRepository extends ICrudRepository<Product> {
    List<Product> getMachineProducts(Integer machineId);
}

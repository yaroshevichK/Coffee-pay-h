package it.academy.repositories.impl;

import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.repositories.IProductRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static it.academy.utils.constants.DataGeneral.MACHINE_CLASS;
import static it.academy.utils.constants.DataGeneral.PRODUCT_CLASS;

public class ProductRepository extends CrudRepository<Product>
        implements IProductRepository {
    public ProductRepository() {
        super(PRODUCT_CLASS);
    }

    @Override
    public List<Product> getMachineProducts(Integer machineId) {
        List<Product> resultList = null;
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            Machine machine = entityManager.find(MACHINE_CLASS, machineId);
            resultList = new ArrayList<>(Optional.ofNullable(machine)
                    .map(Machine::getProducts)
                    .orElse(new HashSet<>()));

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return resultList;
    }
}

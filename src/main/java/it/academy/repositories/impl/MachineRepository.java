package it.academy.repositories.impl;

import it.academy.models.Address;
import it.academy.models.Machine;
import it.academy.models.Model;
import it.academy.models.Product;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IMachineRepository;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static it.academy.utils.constants.DataGeneral.ADDRESS_CLASS;
import static it.academy.utils.constants.DataGeneral.LONG_CLASS;
import static it.academy.utils.constants.DataGeneral.MACHINE_CLASS;
import static it.academy.utils.constants.DataGeneral.MODEL_CLASS;
import static it.academy.utils.constants.DataGeneral.PRODUCT_CLASS;
import static it.academy.utils.constants.DataQuery.PERCENT_STRING;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_ADDRESS;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_BRAND;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_CITY;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_MODEL;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_NAME_MODEL;
import static it.academy.utils.constants.DataUI.ATTR_MACHINE_STREET;

public class MachineRepository extends CrudRepository<Machine>
        implements IMachineRepository {
    public MachineRepository() {
        super(MACHINE_CLASS);
    }

    private Predicate[] getListPredicates(
            HashMap<String, Object> searchFields,
            CriteriaBuilder criteriaBuilder,
            Root<Machine> entityRoot) {

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> pair : searchFields.entrySet()) {
            String key = pair.getKey();
            Object value = pair.getValue();

            if (StringUtils.isNotBlank(key) && value != null) {
                if (String.class.equals(value.getClass()) && StringUtils.isNotBlank((String) value)) {
                    if (ATTR_MACHINE_BRAND.equals((key))
                            || ATTR_MACHINE_NAME_MODEL.equals((key))) {
                        predicates.add(criteriaBuilder
                                .like(entityRoot.get(ATTR_MACHINE_MODEL).get(key),
                                        PERCENT_STRING + value +
                                                PERCENT_STRING));
                    } else if (ATTR_MACHINE_CITY.equals((key))
                            || ATTR_MACHINE_STREET.equals((key))) {
                        predicates.add(criteriaBuilder
                                .like(entityRoot.get(ATTR_MACHINE_ADDRESS).get(key),
                                        PERCENT_STRING + value +
                                                PERCENT_STRING));
                    } else {
                        predicates.add(criteriaBuilder
                                .like(entityRoot.get(key),
                                        PERCENT_STRING + value +
                                                PERCENT_STRING));
                    }
                }
            }

        }

        return predicates.toArray(new Predicate[0]);
    }

    private int getPages(Long countRecords, int pageSize) {
        return (int) (countRecords % pageSize == 0
                ? countRecords / pageSize
                : countRecords / pageSize + 1);
    }

    @Override
    public Pageable<Machine> getPageableRecords(Integer pageSize,
                                                Integer pageNumber,
                                                HashMap<String, Object> searchFields,
                                                String sortField) {
        Pageable<Machine> pageable = Pageable
                .<Machine>builder()
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .searchFields(searchFields)
                .sortField(sortField)
                .build();

        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Machine> entityQuery = criteriaBuilder.createQuery(MACHINE_CLASS);
            Root<Machine> entityRoot = entityQuery.from(MACHINE_CLASS);

            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(LONG_CLASS);
            Root<Machine> countRoot = countQuery.from(MACHINE_CLASS);

            //filter
            Optional.ofNullable(searchFields)
                    .map(fields -> getListPredicates(
                            searchFields,
                            criteriaBuilder,
                            entityRoot)).
                    ifPresent(predicates -> entityQuery.where(criteriaBuilder.and(predicates)));

            //filter count
            Optional.ofNullable(searchFields)
                    .map(fields -> getListPredicates(
                            searchFields,
                            criteriaBuilder,
                            countRoot))
                    .ifPresent(countQuery::where);

            //sorted
            Optional.ofNullable(sortField)
                    .ifPresent(order -> entityQuery.orderBy(criteriaBuilder.asc(entityRoot.get(order))));

            countQuery.select(criteriaBuilder.count(countRoot));
            Long countRecords = entityManager.createQuery(countQuery).getSingleResult();

            int lastPage = getPages(countRecords, pageSize);
            pageable.setLastPage(lastPage);
            pageNumber = Math.min(lastPage, pageNumber);
            pageable.setPageNumber(pageNumber);

            //get records
            int offset = (pageNumber - 1) * pageSize;
            TypedQuery<Machine> resultQuery = entityManager.createQuery(entityQuery);
            resultQuery.setFirstResult(offset);
            resultQuery.setMaxResults(pageSize);

            List<Machine> resultList = resultQuery.getResultList();
            pageable.setRecords(resultList);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return pageable;
    }

    @Override
    public boolean createMachine(Machine machine, Integer addressId, Integer modelId) {
        EntityManager entityManager = super.getEntityManager();
        try {
            Model model = entityManager.find(MODEL_CLASS, modelId);
            Address address = entityManager.find(ADDRESS_CLASS, addressId);

            machine.setModel(model);
            machine.setAddress(address);

            entityManager.persist(machine);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean updateMachine(Machine machine, Integer addressId, Integer modelId) {
        EntityManager entityManager = super.getEntityManager();
        try {
            Model model = entityManager.find(MODEL_CLASS, modelId);
            Address address = entityManager.find(ADDRESS_CLASS, addressId);
            Set<Product> products = entityManager.find(MACHINE_CLASS, machine.getId()).getProducts();

            machine.setModel(model);
            machine.setAddress(address);
            machine.setProducts(products);

            entityManager.merge(machine);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean addProductInMachine(Integer machineId, Integer productId) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Machine machine = entityManager.find(MACHINE_CLASS, machineId);
            Product product = entityManager.find(PRODUCT_CLASS, productId);

            Optional.ofNullable(machine)
                    .map(Machine::getProducts)
                    .ifPresent(products -> products.add(product));

            entityManager.merge(machine);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean deleteProductInMachine(Integer machineId, Integer productId) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Machine machine = entityManager.find(MACHINE_CLASS, machineId);
            Product product = entityManager.find(PRODUCT_CLASS, productId);

            Optional.ofNullable(machine)
                    .map(Machine::getProducts)
                    .ifPresent(products -> products.remove(product));

            entityManager.merge(machine);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }
}

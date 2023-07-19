package it.academy.repositories.impl;

import it.academy.models.Customer;
import it.academy.models.Role;
import it.academy.models.User;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.IUserRepository;
import it.academy.utils.EncryptService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

import static it.academy.utils.constants.DataDB.ATTR_DB_USERNAME;
import static it.academy.utils.constants.DataDB.ATTR_JOIN_CUSTOMER_USER;
import static it.academy.utils.constants.DataGeneral.CUSTOMER_CLASS;
import static it.academy.utils.constants.DataGeneral.ROLE_CUSTOMER;
import static it.academy.utils.constants.DataQuery.FIND_ROLE_BY_NAME;
import static it.academy.utils.constants.DataQuery.FIND_USER_BY_USERNAME;
import static it.academy.utils.constants.DataQuery.PARAM_NAME;
import static it.academy.utils.constants.DataQuery.PARAM_USERNAME;

public class CustomerRepository extends CrudRepository<Customer>
        implements ICustomerRepository {
    private final EncryptService encryptService = new EncryptService();
    private final IUserRepository userRepository = new UserRepository();

    public CustomerRepository() {
        super(CUSTOMER_CLASS);
    }

    private void setEncryptData(User user) {
        byte[] salt = encryptService.generateSalt();
        byte[] encryptedPassword = encryptService
                .getEncryptedPassword(user.getPassword(), salt);
        user.setSalt(encryptService.toHex(salt));
        user.setPassword(encryptService.toHex(encryptedPassword));
    }

    @Override
    public boolean createCustomer(String username, String password, Customer customer) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query queryUser = entityManager.createNamedQuery(FIND_USER_BY_USERNAME);
            queryUser.setParameter(PARAM_USERNAME, username);
            User user = null;
            try {
                user = (User) queryUser.getSingleResult();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (user != null) {
                return false;
            }

            Query query = entityManager.createNamedQuery(FIND_ROLE_BY_NAME);
            query.setParameter(PARAM_NAME, ROLE_CUSTOMER);
            Role role = (Role) query.getSingleResult();

            if (role == null) {
                return false;
            }

            user = User.builder()
                    .username(username)
                    .password(password)
                    .roles(Collections.singleton(role))
                    .build();
            setEncryptData(user);
            customer.setUser(user);

            entityManager.persist(customer);
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
    public Customer findCustomerByUsername(String username) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Customer> query = builder.createQuery(CUSTOMER_CLASS);
            Root<Customer> customerRoot = query.from(CUSTOMER_CLASS);
            query.where(builder.equal(customerRoot.get(ATTR_JOIN_CUSTOMER_USER).get(ATTR_DB_USERNAME), username));

            List<Customer> resultList = entityManager.createQuery(query).getResultList();
            entityManager.getTransaction().commit();
            return resultList.isEmpty() ? null : resultList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updateCustomer(String username, Customer customer) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query queryUser = entityManager.createNamedQuery(FIND_USER_BY_USERNAME);
            queryUser.setParameter(PARAM_USERNAME, username);
            User user = null;
            try {
                user = (User) queryUser.getSingleResult();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (user == null) {
                return false;
            }

            customer.setUser(user);
            entityManager.merge(customer);
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
    public boolean updateCustomer(Customer customer) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer findCustomer = findById(customer.getId());
            customer.setUser(findCustomer.getUser());
            entityManager.merge(customer);
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

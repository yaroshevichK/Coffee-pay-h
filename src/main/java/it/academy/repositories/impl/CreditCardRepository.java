package it.academy.repositories.impl;

import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.repositories.ICreditCardRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.constants.DataDB.ATTR_CARD_CUSTOMER;
import static it.academy.utils.constants.DataDB.ATTR_CUSTOMER_USER;
import static it.academy.utils.constants.DataDB.ATTR_DB_USERNAME;
import static it.academy.utils.constants.DataDB.ATTR_JOIN_CUSTOMER_USER;
import static it.academy.utils.constants.DataGeneral.CREDIT_CARD_CLASS;
import static it.academy.utils.constants.DataGeneral.CUSTOMER_CLASS;

public class CreditCardRepository extends CrudRepository<CreditCard>
        implements ICreditCardRepository {
    public CreditCardRepository() {
        super(CREDIT_CARD_CLASS);
    }

    @Override
    public List<CreditCard> findCreditCardByUsername(String username) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<CreditCard> query = builder.createQuery(CREDIT_CARD_CLASS);
            Root<CreditCard> creditCardRoot = query.from(CREDIT_CARD_CLASS);
            query.where(builder.equal(
                    creditCardRoot.get(ATTR_CARD_CUSTOMER).get(ATTR_CUSTOMER_USER).get(ATTR_DB_USERNAME),
                    username));

            List<CreditCard> resultList = entityManager.createQuery(query).getResultList();
            entityManager.getTransaction().commit();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean createCreditCard(String username, String number) {
        EntityManager entityManager = super.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Customer> query = builder.createQuery(CUSTOMER_CLASS);
            Root<Customer> customerRoot = query.from(CUSTOMER_CLASS);
            query.where(builder.equal(customerRoot.get(ATTR_JOIN_CUSTOMER_USER).get(ATTR_DB_USERNAME), username));

            Customer customer = entityManager.createQuery(query).getSingleResult();
            if (customer == null) {
                return false;
            }

            CreditCard creditCard = CreditCard
                    .builder()
                    .customer(customer)
                    .number(number)
                    .build();

            entityManager.persist(creditCard);
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
    public boolean updateCreditCard(CreditCard creditCard, Integer customerId) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            creditCard.setCustomer(entityManager.find(CUSTOMER_CLASS, customerId));
            entityManager.merge(creditCard);
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

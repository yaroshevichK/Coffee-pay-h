package it.academy.repositories.impl;

import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.models.Discount;
import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.models.Purchase;
import it.academy.models.TypePayment;
import it.academy.repositories.IPurchaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

import static it.academy.utils.constants.DataDB.ATTR_DB_AMOUNT;
import static it.academy.utils.constants.DataDB.ATTR_DB_USERNAME;
import static it.academy.utils.constants.DataDB.ATTR_JOIN_CUSTOMER_USER;
import static it.academy.utils.constants.DataDB.ATTR_JOIN_PURCHASE_CUSTOMER;
import static it.academy.utils.constants.DataGeneral.CREDIT_CARD_CLASS;
import static it.academy.utils.constants.DataGeneral.CUSTOMER_CLASS;
import static it.academy.utils.constants.DataGeneral.DISCOUNT_CLASS;
import static it.academy.utils.constants.DataGeneral.MACHINE_CLASS;
import static it.academy.utils.constants.DataGeneral.PRODUCT_CLASS;
import static it.academy.utils.constants.DataGeneral.PURCHASE_CLASS;
import static it.academy.utils.constants.DataGeneral.TYPE_PAYMENT_CLASS;

public class PurchaseRepository extends CrudRepository<Purchase>
        implements IPurchaseRepository {
    public PurchaseRepository() {
        super(PURCHASE_CLASS);
    }

    @Override
    public boolean createPurchase(Purchase purchase,
                                  Integer customerId,
                                  Integer discountId,
                                  Integer machineId,
                                  Integer productId,
                                  Integer creditCardId,
                                  Integer typePaymentId) {

        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = Optional.ofNullable(customerId)
                    .map(id -> entityManager.find(CUSTOMER_CLASS, id))
                    .orElse(null);
            Discount discount = Optional.ofNullable(discountId)
                    .map(id -> entityManager.find(DISCOUNT_CLASS, id))
                    .orElse(null);
            Machine machine = Optional.ofNullable(machineId)
                    .map(id -> entityManager.find(MACHINE_CLASS, id))
                    .orElse(null);
            Product product = Optional.ofNullable(productId)
                    .map(id -> entityManager.find(PRODUCT_CLASS, id))
                    .orElse(null);
            CreditCard creditCard = Optional.ofNullable(creditCardId)
                    .map(id -> entityManager.find(CREDIT_CARD_CLASS, id))
                    .orElse(null);
            TypePayment typePayment = Optional.ofNullable(typePaymentId)
                    .map(id -> entityManager.find(TYPE_PAYMENT_CLASS, id))
                    .orElse(null);

            purchase.setCustomer(customer);
            purchase.setDiscount(discount);
            purchase.setMachine(machine);
            purchase.setProduct(product);
            purchase.setCreditCard(creditCard);
            purchase.setTypePayment(typePayment);

            if (validate(customer, machine, product,
                    creditCard, typePayment)) return false;

            entityManager.persist(purchase);
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
    public boolean updatePurchase(Purchase purchase,
                                  Integer customerId,
                                  Integer discountId,
                                  Integer machineId, Integer productId,
                                  Integer creditCardId,
                                  Integer typePaymentId) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = Optional.ofNullable(customerId)
                    .map(id -> entityManager.find(CUSTOMER_CLASS, id))
                    .orElse(null);
            Discount discount = Optional.ofNullable(discountId)
                    .map(id -> entityManager.find(DISCOUNT_CLASS, id))
                    .orElse(null);
            Machine machine = Optional.ofNullable(machineId)
                    .map(id -> entityManager.find(MACHINE_CLASS, id))
                    .orElse(null);
            Product product = Optional.ofNullable(productId)
                    .map(id -> entityManager.find(PRODUCT_CLASS, id))
                    .orElse(null);
            CreditCard creditCard = Optional.ofNullable(creditCardId)
                    .map(id -> entityManager.find(CREDIT_CARD_CLASS, id))
                    .orElse(null);
            TypePayment typePayment = Optional.ofNullable(typePaymentId)
                    .map(id -> entityManager.find(TYPE_PAYMENT_CLASS, id))
                    .orElse(null);

            purchase.setCustomer(customer);
            purchase.setDiscount(discount);
            purchase.setMachine(machine);
            purchase.setProduct(product);
            purchase.setCreditCard(creditCard);
            purchase.setTypePayment(typePayment);

            if (validate(customer, machine, product,
                    creditCard, typePayment)) return false;

            entityManager.merge(purchase);
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

    private boolean validate(Customer customer, Machine machine, Product product, CreditCard creditCard, TypePayment typePayment) {
        //validate
        if (customer == null || typePayment == null || machine == null || product == null) {
            return true;
        }
        return typePayment.getUseCreditCard() && creditCard == null;
    }

    @Override
    public Float getSumPurchases(String username) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Float> query = criteriaBuilder.createQuery(Float.class);
            Root<Purchase> purchaseRoot = query.from(PURCHASE_CLASS);
            query.where(criteriaBuilder
                    .equal(purchaseRoot
                            .get(ATTR_JOIN_PURCHASE_CUSTOMER)
                            .get(ATTR_JOIN_CUSTOMER_USER)
                            .get(ATTR_DB_USERNAME), username));

            query.select(criteriaBuilder.sum(purchaseRoot.get(ATTR_DB_AMOUNT)));
            Float amount = entityManager.createQuery(query).getSingleResult();

            entityManager.getTransaction().commit();
            return amount;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }
}

package it.academy.repositories.impl;

import it.academy.models.Discount;
import it.academy.repositories.IDiscountRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static it.academy.utils.constants.DataDB.ATTR_DB_AMOUNT;
import static it.academy.utils.constants.DataDB.ATTR_DB_PERCENT;
import static it.academy.utils.constants.DataGeneral.DISCOUNT_CLASS;

public class DiscountRepository extends CrudRepository<Discount>
        implements IDiscountRepository {
    public DiscountRepository() {
        super(DISCOUNT_CLASS);
    }

    @Override
    public Discount getPercentDiscount(Float amount) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Discount> query = criteriaBuilder.createQuery(DISCOUNT_CLASS);
            Root<Discount> discountRoot = query.from(DISCOUNT_CLASS);
            query.where(criteriaBuilder.le(discountRoot.get(ATTR_DB_AMOUNT), amount))
                    .orderBy(criteriaBuilder.asc(discountRoot.get(ATTR_DB_PERCENT)));

            Discount discount = entityManager.createQuery(query).getSingleResult();
            entityManager.getTransaction().commit();
            return discount;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }
}


//    CriteriaBuilder cbDiscount =
//            entityManager.getCriteriaBuilder();
//    CriteriaQuery<Discount> queryDiscount =
//            cbDiscount.createQuery(DISCOUNT_CLASS);
//
//    Root<Discount> root = queryDiscount.from(DISCOUNT_CLASS);
//            queryDiscount.where(cbDiscount.le(root.get(ATTR_SUM), sum))
//                    .orderBy(cbDiscount.asc(root.get(ATTR_PERCENT)));
//
//                    discount = entityManager
//                    .createQuery(queryDiscount)
//                    .getSingleResult();
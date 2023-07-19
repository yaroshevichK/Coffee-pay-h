package it.academy.repositories.impl;

import it.academy.models.Role;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.ICrudRepository;
import it.academy.utils.HibernateUtil;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static it.academy.utils.constants.DataGeneral.LONG_CLASS;
import static it.academy.utils.constants.DataQuery.PERCENT_STRING;
import static it.academy.utils.constants.DataQuery.STRING_FROM;

public class CrudRepository<TEntity> implements ICrudRepository<TEntity> {
    private final Class<TEntity> cls;

    public CrudRepository(Class<TEntity> cls) {
        this.cls = cls;
    }

    private Predicate[] getListPredicates(
            HashMap<String, Object> searchFields,
            CriteriaBuilder criteriaBuilder,
            Root<TEntity> entityRoot) {

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> pair : searchFields.entrySet()) {
            String key = pair.getKey();
            Object value = pair.getValue();

            if (StringUtils.isNotBlank(key) && value != null) {
                if (String.class.equals(value.getClass())) {
                    if (StringUtils.isNotBlank((String) value)) {
                        predicates.add(criteriaBuilder
                                .like(entityRoot.get(key),
                                        PERCENT_STRING + value +
                                                PERCENT_STRING));
                    }
                } else if (Float.class.equals(value.getClass())) {
                    if ((Float) value != 0) {
                        predicates.add(criteriaBuilder
                                .ge(entityRoot.get(key), (Number) value));
                    }
                } else {
                    predicates.add(criteriaBuilder
                            .equal(entityRoot.get(key), value));
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

    public EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager();
    }

    @Override
    public Pageable<TEntity> getPageableRecords(Integer pageSize,
                                                Integer pageNumber,
                                                HashMap<String, Object> searchFields,
                                                String sortField) {
        Pageable<TEntity> pageable = Pageable
                .<TEntity>builder()
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .searchFields(searchFields)
                .sortField(sortField)
                .build();

        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<TEntity> entityQuery = criteriaBuilder.createQuery(cls);
            Root<TEntity> entityRoot = entityQuery.from(cls);

            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(LONG_CLASS);
            Root<TEntity> countRoot = countQuery.from(cls);

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
            TypedQuery<TEntity> resultQuery = entityManager.createQuery(entityQuery);
            resultQuery.setFirstResult(offset);
            resultQuery.setMaxResults(pageSize);

            List<TEntity> resultList = resultQuery.getResultList();
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
    public boolean save(TEntity entity) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
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
    public TEntity findById(Serializable id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            TEntity entity = entityManager.find(cls, id);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(TEntity entity) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
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
    public List<TEntity> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String clsName = cls.getSimpleName();
            Query query = entityManager.createQuery(STRING_FROM + clsName);
            List<TEntity> resultList = query.getResultList();
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
    public boolean delete(Serializable id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            Optional.ofNullable(entityManager.find(cls, id))
                    .ifPresent(entityManager::remove);

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

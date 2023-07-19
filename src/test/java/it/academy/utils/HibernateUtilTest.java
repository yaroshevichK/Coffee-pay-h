package it.academy.utils;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HibernateUtilTest {
    @Test
    void getEntityManager() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        assertAll(
                () -> assertNotNull(HibernateUtil.FACTORY),
                () -> assertNotNull(entityManager)
        );
        HibernateUtil.closeFactory();
    }
}
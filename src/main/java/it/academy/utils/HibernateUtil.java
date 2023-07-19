package it.academy.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static it.academy.utils.constants.DataGeneral.PERSISTENCE_NAME;


public class HibernateUtil {
    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory(PERSISTENCE_NAME);

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void closeFactory() {
        FACTORY.close();
    }
}

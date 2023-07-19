package it.academy.repositories.impl;

import it.academy.models.Role;
import it.academy.models.User;
import it.academy.repositories.IUserRepository;
import it.academy.utils.EncryptService;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static it.academy.utils.constants.DataGeneral.ROLE_CLASS;
import static it.academy.utils.constants.DataGeneral.USER_CLASS;
import static it.academy.utils.constants.DataQuery.FIND_USER_BY_USERNAME;
import static it.academy.utils.constants.DataQuery.PARAM_USERNAME;

public class UserRepository extends CrudRepository<User>
        implements IUserRepository {
    private final EncryptService encryptService = new EncryptService();

    public UserRepository() {
        super(USER_CLASS);
    }

    @Override
    public User findAuthUser(String username, String pass) {
        User findUser = findUserByUsername(username);
        return checkVerify(pass, findUser);
    }

    private User checkVerify(String pass, User findUser) {
        if (findUser != null) {
            byte[] salt = encryptService.fromHex(findUser.getSalt());
            byte[] encryptPass = encryptService.fromHex(findUser.getPassword());

            boolean verifyUser = encryptService.verifyPassword(pass, encryptPass, salt);

            if (!verifyUser) {
                findUser = null;
            }
        }
        return findUser;
    }

    private void setEncryptData(User user) {
        byte[] salt = encryptService.generateSalt();
        byte[] encryptedPassword = encryptService
                .getEncryptedPassword(user.getPassword(), salt);
        user.setSalt(encryptService.toHex(salt));
        user.setPassword(encryptService.toHex(encryptedPassword));
    }

    @Override
    public User findUserByUsername(String username) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery(FIND_USER_BY_USERNAME);
            query.setParameter(PARAM_USERNAME, username);
            User user = (User) query.getSingleResult();
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updatePasswordUser(String username, String password, String oldPassword) {
        User user = findAuthUser(username, oldPassword);
        if (user == null) {
            return false;
        }

        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            byte[] salt = encryptService.fromHex(user.getSalt());
            byte[] encryptedPassword = encryptService
                    .getEncryptedPassword(password, salt);
            user.setPassword(encryptService.toHex(encryptedPassword));

            entityManager.merge(user);
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
    public boolean createCustomer(User user, String[] roles) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Set<Role> userRoles = Arrays
                    .stream(Optional.ofNullable(roles).orElse(new String[0]))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(StringUtils::isNotBlank)
                    .map(Integer::parseInt)
                    .map(id -> entityManager.find(ROLE_CLASS, id))
                    .collect(Collectors.toSet());

            user.setRoles(userRoles);
            setEncryptData(user);
            entityManager.persist(user);
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
    public boolean updateRolesUser(String username, String[] roles) {
        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createNamedQuery(FIND_USER_BY_USERNAME);
            query.setParameter(PARAM_USERNAME, username);
            User user = (User) query.getSingleResult();
            if (user == null) {
                return false;
            }

            Set<Role> userRoles = Arrays
                    .stream(Optional.ofNullable(roles).orElse(new String[0]))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(StringUtils::isNotBlank)
                    .map(Integer::parseInt)
                    .map(id -> entityManager.find(ROLE_CLASS, id))
                    .collect(Collectors.toSet());

            user.setRoles(userRoles);
            entityManager.merge(user);
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

package it.academy.repositories;

import it.academy.models.User;

public interface IUserRepository extends ICrudRepository<User> {
    User findAuthUser(String username, String pass);

    User findUserByUsername(String username);

    boolean updatePasswordUser(String username, String password, String oldPassword);

    boolean createCustomer(User user, String[] roles);

    boolean updateRolesUser(String username, String[] roles);
}

package it.academy.service;

import it.academy.dto.UserDto;
import it.academy.models.pageable.Pageable;

import java.util.HashMap;

public interface IUserService {
    UserDto findAuthUser(String login, String password);

    UserDto findUserByUsername(String username);

    boolean updatePassUser(String username, String password, String oldPassword);

    Pageable<UserDto> getPageableRecords(Integer pageSize,
                                         Integer pageNumber,
                                         HashMap<String, Object> searchFields,
                                         String sortField);

    boolean createUser(String username, String password, String[] roles);

    boolean updateRolesUser(String username, String[] roles);
}

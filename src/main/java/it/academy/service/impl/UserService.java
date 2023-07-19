package it.academy.service.impl;

import it.academy.dto.UserDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.PageableMapper;
import it.academy.mapper.impl.UserMapper;
import it.academy.models.User;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IRoleRepository;
import it.academy.repositories.IUserRepository;
import it.academy.repositories.impl.RoleRepository;
import it.academy.repositories.impl.UserRepository;
import it.academy.service.IUserService;

import java.util.HashMap;
import java.util.Optional;

public class UserService implements IUserService {
    private final IUserRepository userRepository = new UserRepository();
    private final IRoleRepository roleRepository = new RoleRepository();
    private final IMapper<User, UserDto> userMapper = new UserMapper();
    private final IMapper<Pageable<User>, Pageable<UserDto>> pageableMapper
            = new PageableMapper<>(userMapper);

    @Override
    public Pageable<UserDto> getPageableRecords(Integer pageSize,
                                                Integer pageNumber,
                                                HashMap<String, Object> searchFields,
                                                String sortField) {
        Pageable<User> pageable = userRepository
                .getPageableRecords(pageSize,
                        pageNumber,
                        searchFields,
                        sortField);

        return pageableMapper.entityToDto(pageable);
    }

    @Override
    public UserDto findAuthUser(String login, String password) {
        User user = userRepository.findAuthUser(login, password);
        return Optional.ofNullable(user)
                .map(userMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return Optional.ofNullable(user)
                .map(userMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public boolean updatePassUser(String username, String password, String oldPassword) {
        return userRepository.updatePasswordUser(username, password, oldPassword);
    }

    @Override
    public boolean createUser(String username, String password, String[] roles) {
        User user = User.builder()
                .username(username)
                .password(password)
                .build();
        return userRepository.createCustomer(user, roles);
    }

    @Override
    public boolean updateRolesUser(String username, String[] roles) {
        return userRepository.updateRolesUser(username, roles);
    }

}

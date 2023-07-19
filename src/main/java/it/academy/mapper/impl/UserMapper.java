package it.academy.mapper.impl;

import it.academy.dto.RoleDto;
import it.academy.dto.UserDto;
import it.academy.mapper.IMapper;
import it.academy.models.Role;
import it.academy.models.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper implements IMapper<User, UserDto> {
    private final IMapper<Role, RoleDto> roleMapper = new RoleMapper();

    @Override
    public User dtoToEntity(UserDto userDto) {
        Set<Role> setRoles = Optional
                .ofNullable(userDto.getRoles())
                .orElse(new HashSet<>())
                .stream()
                .map(roleMapper::dtoToEntity)
                .collect(Collectors.toSet());

        return User.builder()
                .username(userDto.getUsername())
                .roles(setRoles)
                .build();
    }

    @Override
    public UserDto entityToDto(User user) {
        Set<RoleDto> setRoles = Optional
                .ofNullable(user.getRoles())
                .orElse(new HashSet<>())
                .stream()
                .map(roleMapper::entityToDto)
                .collect(Collectors.toSet());

        return UserDto.builder()
                .username(user.getUsername())
                .roles(setRoles)
                .build();
    }
}

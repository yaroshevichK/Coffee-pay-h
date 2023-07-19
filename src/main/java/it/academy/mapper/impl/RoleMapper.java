package it.academy.mapper.impl;

import it.academy.dto.RoleDto;
import it.academy.mapper.IMapper;
import it.academy.models.Role;

public class RoleMapper implements IMapper<Role, RoleDto> {
    @Override
    public Role dtoToEntity(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .build();
    }

    @Override
    public RoleDto entityToDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}

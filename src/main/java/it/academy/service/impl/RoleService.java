package it.academy.service.impl;

import it.academy.dto.RoleDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.PageableMapper;
import it.academy.mapper.impl.RoleMapper;
import it.academy.models.Role;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IRoleRepository;
import it.academy.repositories.impl.RoleRepository;
import it.academy.service.IRoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository = new RoleRepository();
    private final IMapper<Role, RoleDto> roleMapper = new RoleMapper();
    private final IMapper<Pageable<Role>, Pageable<RoleDto>> pageableMapper
            = new PageableMapper<>(roleMapper);

    @Override
    public Pageable<RoleDto> getPageableRecords(Integer pageSize,
                                                Integer pageNumber,
                                                HashMap<String, Object> searchFields,
                                                String sortField) {
        Pageable<Role> pageable = roleRepository
                .getPageableRecords(pageSize,
                        pageNumber,
                        searchFields,
                        sortField);

        return pageableMapper.entityToDto(pageable);
    }

    @Override
    public boolean createRole(RoleDto roleDto) {
        Role role = roleMapper.dtoToEntity(roleDto);
        return roleRepository.save(role);
    }

    @Override
    public RoleDto findRoleById(Integer id) {
        Role role = roleRepository.findById(id);
        return roleMapper.entityToDto(role);
    }

    @Override
    public boolean updateRole(RoleDto roleDto) {
        Role role = roleMapper.dtoToEntity(roleDto);
        return roleRepository.update(role);
    }

    @Override
    public boolean deleteRoleById(Integer id) {
        return roleRepository.delete(id);
    }

    @Override
    public List<RoleDto> findAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return Optional.ofNullable(roleList)
                .orElse(new ArrayList<>())
                .stream()
                .map(roleMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

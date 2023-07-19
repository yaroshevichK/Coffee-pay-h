package it.academy.service;

import it.academy.dto.RoleDto;
import it.academy.models.pageable.Pageable;

import java.util.HashMap;
import java.util.List;

public interface IRoleService {
    Pageable<RoleDto> getPageableRecords(Integer pageSize,
                                         Integer pageNumber,
                                         HashMap<String, Object> searchFields,
                                         String sortField);

    boolean createRole(RoleDto roleDto);

    RoleDto findRoleById(Integer id);

    boolean updateRole(RoleDto roleDto);

    boolean deleteRoleById(Integer id);

    List<RoleDto> findAllRoles();
}

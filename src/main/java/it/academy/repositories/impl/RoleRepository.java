package it.academy.repositories.impl;

import it.academy.models.Role;
import it.academy.repositories.IRoleRepository;

import static it.academy.utils.constants.DataGeneral.ROLE_CLASS;

public class RoleRepository extends CrudRepository<Role>
        implements IRoleRepository {
    public RoleRepository() {
        super(ROLE_CLASS);
    }

}

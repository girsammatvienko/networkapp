package com.group.networkapp.repository;

import com.group.networkapp.domain.RoleEnum;
import com.group.networkapp.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(RoleEnum name);
}

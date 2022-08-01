package com.project.toystore.service;

import com.project.toystore.entities.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);
    List<Role> findAllRoles();

    Role findById(Long id);
}

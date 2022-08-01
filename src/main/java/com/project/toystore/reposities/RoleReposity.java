package com.project.toystore.reposities;

import com.project.toystore.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleReposity extends JpaRepository<Role,Long> {
    Role findByName(String roleName);
}

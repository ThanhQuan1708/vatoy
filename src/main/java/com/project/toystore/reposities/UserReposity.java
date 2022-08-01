package com.project.toystore.reposities;

import com.project.toystore.entities.Role;
import com.project.toystore.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository

public interface UserReposity extends JpaRepository<User,Long> {
    User findByEmail(String email);

    Page<User> findByRoleIn(Set<Role> roles, Pageable of);

    List<User> findByRoleIn(Set<Role> roles);


}


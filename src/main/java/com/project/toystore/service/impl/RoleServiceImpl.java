package com.project.toystore.service.impl;

import com.project.toystore.entities.Role;
import com.project.toystore.reposities.RoleReposity;
import com.project.toystore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
@ComponentScan
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleReposity roleRepo;

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepo.findById(id).get();
    }
}

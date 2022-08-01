package com.project.toystore.service.impl;

import com.project.toystore.dto.UserDTO;
import com.project.toystore.entities.Role;
import com.project.toystore.entities.User;
import com.project.toystore.reposities.RoleReposity;
import com.project.toystore.reposities.UserReposity;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserReposity userRepo;
    @Autowired
    private RoleReposity roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepo.findByName("ROLE_MEMBER"));
        user.setRole(roleSet);
        return userRepo.save(user);
    }

    @Override
    public User findById(long id) {
        return  userRepo.findById(id).get();
    }

    @Override
    public User createAccount(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setTel(dto.getTel());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepo.findByName(dto.getRoleName()));
        user.setRole(roleSet);
        return userRepo.save(user);
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public void changePassword(User user, String newPass) {
        user.setPassword(bCryptPasswordEncoder.encode(newPass));
        userRepo.save(user);
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public Page<User> getUserByRole(Set<Role> roles, int page) {
        return userRepo.findByRoleIn(roles, PageRequest.of(page-1,6));
    }

    @Override
    public List<User> getUserByRole(Set<Role> roles) {
        return userRepo.findByRoleIn(roles);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}

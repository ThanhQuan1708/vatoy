package com.project.toystore.service;

import com.project.toystore.dto.UserDTO;
import com.project.toystore.entities.Role;
import com.project.toystore.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface UserService {
    User findByEmail (String email);
    User register(User user);
    User findById(long id);
    User createAccount(UserDTO dto);
    User update(User user);
    void changePassword(User user, String newPass);
    void delete(long id);
    Page<User> getUserByRole(Set<Role> roles, int page);
    List<User> getUserByRole(Set<Role> roles);
    List<User> getAllUser();


}

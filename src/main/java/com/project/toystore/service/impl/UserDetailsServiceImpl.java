package com.project.toystore.service.impl;

import com.project.toystore.entities.Role;
import com.project.toystore.entities.User;
import com.project.toystore.reposities.UserReposity;
//import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//    org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User
    @Autowired
    private UserReposity userReposity;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userReposity.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User with the email "+username+"doesn't found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roleSet  = user.getRole();
        for(Role r : roleSet){
            grantedAuthorities.add(new SimpleGrantedAuthority(r.getName()));
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(),grantedAuthorities);
    }
}

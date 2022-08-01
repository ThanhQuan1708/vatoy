package com.project.toystore.configuration;

import com.project.toystore.entities.Role;
import com.project.toystore.entities.User;
import com.project.toystore.reposities.RoleReposity;
import com.project.toystore.reposities.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataCreater implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserReposity userReposity;
    @Autowired
    private RoleReposity roleReposity;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(userReposity.findByEmail("admin@gmail.com")==null){
            User ad = new User();
            ad.setName("admin");
//            ad.setPassword("123456789");
            ad.setPassword(encoder.encode("123456789"));
            ad.setEmail("admin@gmail.com");
            ad.setTel("037212121");
            ad.setAddress("TP Thu duc");
            HashSet<Role> roles = new HashSet<Role>();
            roles.add(roleReposity.findByName("ROLE_ADMIN"));
            roles.add(roleReposity.findByName("ROLE_MEMBER"));
            ad.setRole(roles);
            userReposity.save(ad);
        }
    }
}

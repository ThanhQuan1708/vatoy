package com.project.toystore.reposities;

import com.project.toystore.entities.Cart;
import com.project.toystore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartReposity extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}

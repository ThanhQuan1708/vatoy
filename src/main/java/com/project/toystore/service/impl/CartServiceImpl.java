package com.project.toystore.service.impl;

import com.project.toystore.entities.Cart;
import com.project.toystore.entities.User;
import com.project.toystore.reposities.CartReposity;
import com.project.toystore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public class CartServiceImpl implements CartService {
    @Autowired
    private CartReposity cartRepo;

    @Override
    public Cart getCartByUser(User user) {
        return cartRepo.findByUser(user);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }
}

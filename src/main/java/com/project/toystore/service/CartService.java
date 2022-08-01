package com.project.toystore.service;

import com.project.toystore.entities.Cart;
import com.project.toystore.entities.User;

public interface CartService {
    Cart getCartByUser(User user);
    Cart save(Cart cart);
}

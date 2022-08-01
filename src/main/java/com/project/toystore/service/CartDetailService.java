package com.project.toystore.service;

import com.project.toystore.entities.Cart;
import com.project.toystore.entities.CartDetail;
import com.project.toystore.entities.Product;

import java.util.List;

public interface CartDetailService {
    List<CartDetail> getCartDetailByCart(Cart cart);
    CartDetail getCartDetailByProductAndCart(Product product, Cart cart);
    CartDetail save(CartDetail cartDetail);
    void delete(CartDetail cartDetail);
    void deleteAll(List<CartDetail> cartDetails);
}

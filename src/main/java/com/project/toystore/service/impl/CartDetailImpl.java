package com.project.toystore.service.impl;

import com.project.toystore.entities.Cart;
import com.project.toystore.entities.CartDetail;
import com.project.toystore.entities.Product;
import com.project.toystore.reposities.CartDetailReposity;
import com.project.toystore.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
@ComponentScan
@Service
public class CartDetailImpl implements CartDetailService {
    @Autowired
    private CartDetailReposity cartDetailRepo;

    @Override
    public List<CartDetail> getCartDetailByCart(Cart cart) {
        return cartDetailRepo.findByCart(cart);
    }
    @Override
    public CartDetail getCartDetailByProductAndCart(Product product, Cart cart) {
        return cartDetailRepo.findByProductAndCart(product,cart);
    }
    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepo.save(cartDetail);
    }

    @Override
    public void delete(CartDetail cartDetail) {
        cartDetailRepo.delete(cartDetail);
    }


    @Override
    public void deleteAll(List<CartDetail> cartDetails) {
        cartDetailRepo.deleteAll(cartDetails);
    }
}

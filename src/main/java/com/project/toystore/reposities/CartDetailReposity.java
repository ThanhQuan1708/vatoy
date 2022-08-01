package com.project.toystore.reposities;

import com.project.toystore.entities.Cart;
import com.project.toystore.entities.CartDetail;
import com.project.toystore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailReposity extends JpaRepository<CartDetail,Long> {
    CartDetail findByProductAndCart(Product product, Cart cart);
    List<CartDetail> findByCart(Cart cart);
}

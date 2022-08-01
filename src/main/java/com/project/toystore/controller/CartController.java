package com.project.toystore.controller;

import com.project.toystore.entities.Cart;
import com.project.toystore.entities.CartDetail;
import com.project.toystore.entities.Product;
import com.project.toystore.entities.User;
import com.project.toystore.service.CartDetailService;
import com.project.toystore.service.CartService;
import com.project.toystore.service.ProductService;
import com.project.toystore.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@SessionAttributes("loggedInUser")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }
    public User getSessionUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("loggedInUser");
    }
    @GetMapping("/cart")
    public String cartDisplay(HttpServletRequest request, Model model){
        User currUser = getSessionUser(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<Long, String> quantity = new HashMap<Long, String>();
        List<Product> products = new ArrayList<Product>();
        if(auth==null|| auth.getPrincipal() == "anonhymousUser"){
            Cookie[] cs = request.getCookies();
            Set<Long> idList = new HashSet<Long>();
            for(int i = 0 ; i<cs.length;i++){
                if(cs[i].getName().matches("[0-9]+]")){
                    idList.add(Long.parseLong(cs[i].getName()));
                    quantity.put(Long.parseLong(cs[i].getName()), cs[i].getValue());
                }
            }
            products = productService.getAllProductByList(idList);
        }else{
            Cart cart = cartService.getCartByUser(currUser);
            if(cart!= null){
                List<CartDetail> detailList = cartDetailService.getCartDetailByCart(cart);
                for (CartDetail cd : detailList){
                    products.add(cd.getProduct());
                    quantity.put(cd.getProduct().getId(),Integer.toString(cd.getQuantity()));
                }
            }
        }
        model.addAttribute("checkEmpty",products.size());
        model.addAttribute("cart",products);
        model.addAttribute("quantity",quantity);

        return "client/cart";
    }
}

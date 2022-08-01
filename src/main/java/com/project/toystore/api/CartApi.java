package com.project.toystore.api;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.entities.Cart;
import com.project.toystore.entities.CartDetail;
import com.project.toystore.entities.Product;
import com.project.toystore.entities.User;
import com.project.toystore.reposities.UserReposity;
import com.project.toystore.service.CartDetailService;
import com.project.toystore.service.CartService;
import com.project.toystore.service.ProductService;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/cart")
@SessionAttributes("loggedInUser")
public class CartApi {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartDetailService cartDetailService;
    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }
    public User getSessionUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("loggedInUser");
    }
    @GetMapping("/addProduct")
    public ObjectReponse addToCart(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        ObjectReponse obj = new ObjectReponse();
        Product pro = productService.getProductById(Long.parseLong(id));
        if (pro.getInStorage() == 0) {
            obj.setStatus("fail");
            return obj;
        }
        User current = getSessionUser(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == "anonymousUser") {
            Cookie cl[] = request.getCookies();
            boolean found = false;
            for (int i = 0; i < cl.length; i++) {
                if (cl[i].getName().equals(id)) {
                    cl[i].setValue(Integer.toString(Integer.parseInt(cl[i].getValue()) + 1));
                    cl[i].setPath("/toystore");
                    cl[i].setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cl[i]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                Cookie cookie = new Cookie(id, "1");
                cookie.setPath("/toystore");
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
            }
        }
             else {
                Cart cart = cartService.getCartByUser(current);
                if (cart == null) {
                    cart = new Cart();
                    cart.setUser(current);
                    cart = cartService.save(cart);
                }
                CartDetail cd = cartDetailService.getCartDetailByProductAndCart(pro, cart);
                if (cd == null) {
                    cd = new CartDetail();
                    cd.setCart(cart);
                    cd.setProduct(pro);
                    cd.setQuantity(1);
                } else {
                    cd.setQuantity(cd.getQuantity() + 1);
                }
                cartDetailService.save(cd);
            }
        obj.setStatus("success");
        return obj;
    }
    @GetMapping("/changeProductQuantity")
    public ObjectReponse changeQuantity(@RequestParam String id, @RequestParam String value, HttpServletRequest request, HttpServletResponse response){
        ObjectReponse obj = new ObjectReponse();
        User current = getSessionUser(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth.getPrincipal() == "anonymousUser"){
            Cookie cl[] = request.getCookies();
            for (int i = 0; i < cl.length; i++) {
                if (cl[i].getName().equals(id)){
                    cl[i].setValue(value);
                    cl[i].setMaxAge(60*60*24*7);
                    cl[i].setPath("/toystore");
                    response.addCookie(cl[i]);
                    break;
                }
            }
        }else{
            Cart cart = cartService.getCartByUser(current);
            Product pro = productService.getProductById(Long.parseLong(id));
            CartDetail cd = cartDetailService.getCartDetailByProductAndCart(pro,cart);
            cd.setQuantity(Integer.parseInt(value));
            cd = cartDetailService.save(cd);
        }
        obj.setStatus("success");
        return obj;
    }
    @GetMapping("/deleteProductOnCart")
    public ObjectReponse deleteProduct(@RequestParam String id, HttpServletRequest request, HttpServletResponse response){
        ObjectReponse obj = new ObjectReponse();
        User current = getSessionUser(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth.getPrincipal() == "anonymousUser"){
            Cookie cl[] = request.getCookies();
            for (int i = 0; i <cl.length; i++) {
                if(cl[i].getName().equals(id)){
                    cl[i].setMaxAge(0);
                    cl[i].setPath("/toystore");
                    response.addCookie(cl[i]);
                    break;
                }
            }
        }else{
            Cart cart = cartService.getCartByUser(current);
            Product pro = productService.getProductById(Long.parseLong(id));
            CartDetail cd = cartDetailService.getCartDetailByProductAndCart(pro,cart);
            cartDetailService.delete(cd);
        }
        obj.setStatus("success");
        return obj;
    }
}


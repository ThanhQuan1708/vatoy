package com.project.toystore.controller;

import com.project.toystore.entities.*;
import com.project.toystore.service.*;
import com.project.toystore.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.jdo.annotations.Order;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@SessionAttributes("loggedInUser")
public class CheckOutController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailService orderDetailService;
    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }
    public User getSessionUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("loggedInUser");
    }
    @GetMapping("/checkout")
    public String checkoutDisplay(HttpServletRequest request, Model model){
        User curUser = getSessionUser(request);
        Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        List<Product> products = new ArrayList<Product>();
        Map<Long, String> quantity = new HashMap<Long,String>();

        if (auth == null || auth.getPrincipal()=="anonymousUser"){      //get from cookie
            Cookie[] cs = request.getCookies();
            Set<Long> idList = new HashSet<Long>();
            for (int i = 0 ;i< cs.length; i++){
                if(cs[i].getName().matches("[0-9]+")){
                    idList.add(Long.parseLong(cs[i].getName()));
                    quantity.put(Long.parseLong(cs[i].getName()), cs[i].getValue());
                }
            }
            products = productService.getAllProductByList(idList);
        }else{          //get from database
            Cart cart = cartService.getCartByUser(curUser);
            if(cart != null){
                List<CartDetail> detailList = cartDetailService.getCartDetailByCart(cart);
                for(CartDetail cartDetail : detailList){
                    products.add(cartDetail.getProduct());
                    quantity.put(cartDetail.getProduct().getId(), Integer.toString(cartDetail.getQuantity()));
                }
            }
        }
        model.addAttribute("cart",products);
        model.addAttribute("quantity",quantity);
        model.addAttribute("user", curUser);
        model.addAttribute("order",new Orderr());

        return "client/checkout";
    }
    @PostMapping("/thankyou")
    public String thankYou(@ModelAttribute("order") Orderr order, HttpServletRequest request, HttpServletResponse response, Model model){
        order.setOrderDate(new Date());
        order.setStatus("Đang chờ giao");
        User currUser = getSessionUser(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Product> products = new ArrayList<Product>();
        Map<Long,String> quantity = new HashMap<Long,String>();
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        if(auth==null || auth.getPrincipal()=="anonymousUser"){     //get from cookie
            Orderr o = orderService.save(order);
            Cookie cs[] = request.getCookies();
            Set<Long> idList = new HashSet<Long>();
            for (int i = 1; i < cs.length; i++ ){
                if(cs[i].getName().matches("[0-9]+]")){
                    idList.add(Long.parseLong(cs[i].getName()));
                    quantity.put(Long.parseLong(cs[i].getName()), cs[i].getValue());
                }
            }
            products = productService.getAllProductByList(idList);
            for (Product p : products){
                OrderDetail od = new OrderDetail();
                od.setProduct(p);
                od.setOrderQuantity(Integer.parseInt(quantity.get(p.getId())));
                od.setPrice(Integer.parseInt(quantity.get(p.getId()))*p.getPrice());
                od.setOrder(o);
                orderDetailList.add(od);
            }
        }else{          //get from database
            order.setCustomer(currUser);
            Orderr o = orderService.save(order);
            Cart cart = cartService.getCartByUser(currUser);
            List<CartDetail> cartDetailList = cartDetailService.getCartDetailByCart(cart);
            for (CartDetail cartDetail : cartDetailList){
                OrderDetail od = new OrderDetail();
                od.setProduct(cartDetail.getProduct());
                od.setPrice(cartDetail.getQuantity()*cartDetail.getProduct().getPrice());
                od.setOrderQuantity(cartDetail.getQuantity());
                od.setOrder(o);
                orderDetailList.add(od);

                products.add(cartDetail.getProduct());
                quantity.put(cartDetail.getProduct().getId(), Integer.toString(cartDetail.getQuantity()));
            }
        }
        orderDetailService.save(orderDetailList);
        model.addAttribute("order",order);
        model.addAttribute("cart",products);
        model.addAttribute("quantity",quantity);
        return "client/thankyou";
    }
    public void clear(HttpServletRequest request, HttpServletResponse response){
        User currUser =getSessionUser(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth.getPrincipal()=="anonymousUser"){       //get from cookie
            Cookie cs[] = request.getCookies();
            for (int i = 0; i<cs.length; i++){
                if(cs[i].getName().matches("[0-9]+")){
                    cs[i].setMaxAge(0);
                    cs[i].setPath("/toystore");
                    response.addCookie(cs[i]);
                }
            }
        }else{        //get from database
            Cart cart = cartService.getCartByUser(currUser);
            List<CartDetail> cartDetails = cartDetailService.getCartDetailByCart(cart);
            cartDetailService.deleteAll(cartDetails);
        }
    }
}

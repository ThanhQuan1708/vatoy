package com.project.toystore.controller;

import com.project.toystore.dto.ToDoDTO;
import com.project.toystore.entities.Brand;
import com.project.toystore.entities.Role;
import com.project.toystore.entities.User;
import com.project.toystore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ComponentScan
@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private RecAgeService recAgeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ContactService contactService;

    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }
    @GetMapping
    public String adminPage(Model model){
        ToDoDTO todoList = new ToDoDTO();
        todoList.setNewOrderQuantity(orderService.countByStatus("Đang chờ giao"));
        todoList.setNewContactQuantity(orderService.countByStatus("Đang chờ duyệt"));
        todoList.setInProcessOrder(contactService.countByStatus("Đang chờ trả lời"));
        model.addAttribute("todoList",todoList);
        return "admin/home";
    }
    @GetMapping("/category")
    public String categoryManage(){return "admin/categoryManage";}
    @GetMapping("/brand")
    public String brandManage(){return "admin/brandManage";}
    @GetMapping("/rec-age")
    public String recAgeManage(){return "admin/recAgeManage";}
    @GetMapping("/contact")
    public String contactManage(){
        return "admin/contactManage";
    }
    @GetMapping("/product")
    public String productMange(Model model){
        model.addAttribute("listCategory", categoryService.getAllCategory());
        model.addAttribute("listBrand", brandService.getAllBrand());
        model.addAttribute("listRecAge",recAgeService.getAllRecAge());
        return "admin/productManage";
    }
    @GetMapping("/profile")
    public String profileDisplay(Model model, HttpServletRequest request){
        model.addAttribute("user",getSessionUser(request));
        return "admin/profile";
    }
    @PostMapping("/profile/update")
    public String updateUser(@ModelAttribute User user, HttpServletRequest request){
        User current = getSessionUser(request);
        current.setAddress(user.getAddress());
        current.setName(user.getName());
        current.setTel(user.getTel());
        userService.update(current);
        return "redirect:/admin/profile";
    }
    @GetMapping("/order")
    public String orderMange(Model model){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findByName("ROLE_SHIPPER"));
        List<User> shippers =userService.getUserByRole(roleSet);
        for (User shipper: shippers) {
            shipper.setOrderList(orderService.findByStatusAndShipper("Đang giao",shipper));
        }
        model.addAttribute("listShipper",shippers);
        return "admin/orderMange";
    }
    @GetMapping("/account")
    public String roleMange(Model model){
        model.addAttribute("listRole",roleService.findAllRoles());
        return "admin/account";
    }
    public User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

}


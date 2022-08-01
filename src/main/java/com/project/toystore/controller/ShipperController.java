package com.project.toystore.controller;

import com.project.toystore.entities.User;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shipper")
@SessionAttributes("loggedInUser")
public class ShipperController {
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
    @GetMapping(value = {"","/order"})
    public String shipperDisplay(Model model){
        return "shipper/orderManage";
    }
    @GetMapping("/profile")
    public String profile(Model model,HttpServletRequest request){
        model.addAttribute("user",getSessionUser(request));
        System.out.println(getSessionUser(request).toString());
        return "shipper/profile";
    }
    @GetMapping("/profile/update")
    public String update(@ModelAttribute User user, HttpServletRequest request){
        User currUser = getSessionUser(request);
        currUser.setName(user.getName());
        currUser.setTel(user.getTel());
        currUser.setAddress(user.getAddress());
        userService.update(currUser);
        return "redirect:/shipper/profile";
    }

}

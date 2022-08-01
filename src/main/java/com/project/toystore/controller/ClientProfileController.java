package com.project.toystore.controller;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.dto.PasswordDTO;
import com.project.toystore.entities.User;
import com.project.toystore.service.OrderService;
import com.project.toystore.service.ProductService;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class ClientProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }
    public User getSessionUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("loggedInUser");
    }
    @GetMapping("/account")
    public String accountDisplay(HttpServletRequest request, Model model){
        User currUser = getSessionUser(request);
        model.addAttribute("user",currUser);
        return "client/account";
    }
    @GetMapping("/changeInfo")
    public String changeInfoDisplay(HttpServletRequest request, Model model){
        User currUser = getSessionUser(request);
        model.addAttribute("user",currUser);
        return "client/info";
    }
    @GetMapping("/changePassword")
    public String changePasswordDisplay(){
        return "client/changePass";
    }
    @PostMapping("/updateInfo")
    @ResponseBody
    public ObjectReponse changeInfo(HttpServletRequest request, @RequestBody User user){
        User curr = getSessionUser(request);
        curr.setName(user.getName());
        curr.setTel(user.getTel());
        curr.setAddress(user.getAddress());
        userService.update(curr);
        return new ObjectReponse();
    }
    @PostMapping("/updatePass")
    @ResponseBody
    public ObjectReponse changePass(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO){
        User currUser = getSessionUser(request);
        if(!encoder.matches(passwordDTO.getOldPassword(), currUser.getPassword())){
            ObjectReponse obj = new ObjectReponse();
            obj.setStatus("old");
            return obj;
        }
        userService.changePassword(currUser,passwordDTO.getNewPassword());
        return new ObjectReponse();
    }


}

package com.project.toystore.controller;

import com.project.toystore.entities.User;
import com.project.toystore.service.SecurityService;
import com.project.toystore.service.UserService;
import com.project.toystore.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@ComponentScan
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @GetMapping("/register")
    public String registerDisplay(Model model){
        model.addAttribute("newUser", new User());
        return "client/register";
    }
    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("newUser") @Valid User user, BindingResult result, Model model){
        userValidator.validate(user, result);
        if(result.hasErrors()){
            return "client/register";
        }
        userService.register(user);
//        securityService.autologin(user.getEmail(),user.getConfirmPassword());
        return "redirect:/";
    }


}

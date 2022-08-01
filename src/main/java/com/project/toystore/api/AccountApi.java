package com.project.toystore.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.dto.UserDTO;
import com.project.toystore.entities.Role;
import com.project.toystore.entities.User;
import com.project.toystore.service.RoleService;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/account")
public class AccountApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public Page<User> getUserByRole(@RequestParam("name") String nameRole, //String name
                                    @RequestParam(defaultValue = "1") int page) {

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findByName(nameRole));

        return userService.getUserByRole(roleSet, page);
    }
    @GetMapping("/alls")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }
    @PostMapping("/save")
    public ObjectReponse saveAccount(@RequestBody @Valid UserDTO dto, BindingResult result, Model model) {

        ObjectReponse obj = new ObjectReponse();

        if(userService.findByEmail(dto.getEmail()) != null) {
            result.rejectValue("email", "error.email","Email is exist");
        }
        if(!dto.getConfirmPassword().equals(dto.getPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword","Password doesn't match");
        }

        if (result.hasErrors()) {
            setErrorsForObjectReponse(result, obj);
        } else {
            obj.setStatus("success");
            userService.createAccount(dto);
        }
        return obj;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTaiKhoan(@PathVariable long id) {
        userService.delete(id);
    }
    public void setErrorsForObjectReponse(BindingResult result, ObjectReponse obj) {

        Map<String, String> errors = result.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        obj.setErrorMassage(errors);
        obj.setStatus("fail");

        List<String> keys = new ArrayList<String>(errors.keySet());
        for (String key: keys) {
            System.out.println(key + ": " + errors.get(key));
        }

        errors = null;
    }
}


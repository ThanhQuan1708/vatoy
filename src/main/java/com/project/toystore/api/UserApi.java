package com.project.toystore.api;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.dto.PasswordDTO;
import com.project.toystore.dto.UserDTO;
import com.project.toystore.entities.User;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id){
        User user = userService.findById(id);
        return user;
    }
    @PostMapping("/change-password")
    public ObjectReponse changePassword(@RequestBody @Valid PasswordDTO dto, BindingResult result, HttpServletRequest request ){
        User curUser = getSessionUser(request);
        ObjectReponse obj = new ObjectReponse();
        if(!bCryptPasswordEncoder.matches(dto.getOldPassword(), curUser.getPassword())){
            result.rejectValue("oldPassword","error.oldPassword","Old password is not correct");
        }
        if(!dto.getNewPassword().equals(dto.getConfirmNewPassword())){
            result.rejectValue("confirmNewPassword","error.confirmNewPassword","Confirm password is not match");
        }
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            List<FieldError> errorsList = result.getFieldErrors();
            for (FieldError error : errorsList ) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            obj.setErrorMassage(errors);
            obj.setStatus("fail");
            errors = null;
        } else {
            userService.changePassword(curUser, dto.getNewPassword());
            obj.setStatus("success");
        }
        return obj;
    }
    public User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

}

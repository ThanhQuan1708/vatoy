package com.project.toystore.validator;

import com.project.toystore.entities.User;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmpty(errors,"name","error.name","This field is not null");
        ValidationUtils.rejectIfEmpty(errors,"tel","error.tel","This field is not null");
        ValidationUtils.rejectIfEmpty(errors,"address","error.address","This field is not null");

        ValidationUtils.rejectIfEmpty(errors,"email","error.email","This email is not null");
        //check valid email xxx@gmail.com
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if(!pattern.matcher(user.getEmail()).matches()){
            errors.rejectValue("email","error.email","Email is not valid");
        }
        //check exist email
        if(userService.findByEmail(user.getEmail())!= null){
            errors.rejectValue("email","error.email","Email is exists");
        }
        //check pass
        //empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","error.password","Password is not null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword","error.confirmPassword",
                "Confirm password is not null");
        //length
        if(user.getPassword().length()<8|| user.getPassword().length() > 32){
            errors.rejectValue("password","error.password","Password length 8-32 digits");
        }
        //match
        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword","error.confirmPassword","Confirm password doesn't match");
        }
    }
}

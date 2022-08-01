package com.project.toystore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class UserDTO {
    private String id;

    @NotEmpty(message = "This field is not null")
    @Email(message = "Enter the correct email")
    private String email;

    @NotEmpty(message = "This field is not null")
    @Length(min = 8, max = 30, message = "Password required 8-32 characters")
    private String password;
    private String confirmPassword;
    @NotEmpty(message = "This field is not null")
    private String name;
    @NotEmpty(message = "This field is not null")
    private String address;
    @NotEmpty(message = "This field is not null")
    private String tel;

    private String roleName;


}

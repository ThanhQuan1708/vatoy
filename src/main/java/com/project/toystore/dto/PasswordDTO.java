package com.project.toystore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class PasswordDTO {
    @NotEmpty(message = "This field is not null")
    private String oldPassword;

    @NotEmpty(message = "This field is not null")
    @Length(min=8, max=32, message="Password required 8 - 32 character ")
    private String newPassword;

    @NotEmpty(message = "This field is not null")
    private String confirmNewPassword;

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmNewPassword='" + confirmNewPassword + '\'' +
                '}';
    }
}

package com.project.toystore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Getter
@Setter
public class ContactDTO {
    private long id;
    @NotEmpty(message = "This field is not null")
    private String contactContent;
    private String title;
    @NotEmpty(message = "This field is not null")
    @Email(message = "Enter the correct email")
    private String email;
    private Date dateReply;

}

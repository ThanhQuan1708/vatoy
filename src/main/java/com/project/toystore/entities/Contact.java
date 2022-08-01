package com.project.toystore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
//@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    private String content;

    private String replyContent;

    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone="GMT+7")
    private Date dateCreate;

    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone="GMT+7")
    private Date dateReply;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_reply")
    private User id_user;
}

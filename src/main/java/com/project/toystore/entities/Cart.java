package com.project.toystore.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long total;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}

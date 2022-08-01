package com.project.toystore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @Transient
    @JsonIgnore
    private String confirmPassword;
    private String address;
    private String tel;

//    @OneToMany(fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "id_user"),
    inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> role;

    @Transient
    @JsonIgnore
    private List<Orderr> orderList;
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}

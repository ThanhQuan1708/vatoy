package com.project.toystore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name="id_product")
    private Product product;
    private long price;
    private int orderQuantity;

    private int receiveQuantity;

    @ManyToOne
    @JoinColumn(name = "id_order")
    @JsonIgnore
    private Orderr order;




}

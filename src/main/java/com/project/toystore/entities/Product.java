package com.project.toystore.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Getter
@Setter
//@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String name;
    private long price;
    private int inStorage;
    private int sellQuantity;
    private String description;
    private String topic;
    private String origin;
    private String material;

    @Transient
    @JsonIgnore
    private MultipartFile image;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_age")
    private RecAge recAge;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inStorage=" + inStorage +
                ", sellQuantity=" + sellQuantity +
                ", description='" + description + '\'' +
                ", topic='" + topic + '\'' +
                ", origin='" + origin + '\'' +
                ", material='" + material + '\'' +
                ", image=" + image +
                ", category=" + category +
                ", brand=" + brand +
                ", recAge=" + recAge +
                '}';
    }
}

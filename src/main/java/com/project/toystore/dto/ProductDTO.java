package com.project.toystore.dto;

import com.project.toystore.entities.Brand;
import com.project.toystore.entities.Category;
import com.project.toystore.entities.RecAge;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter
@Setter
public class ProductDTO {
    private String id;
    private String name;
    private String price;
    private String inStorage;
//    private String sellQuantity;
    private String description;
    private String topic;
    private String origin;
    private String material;

    private MultipartFile image;

    private long id_category;
    private long id_brand;
    private long id_recAge;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inStorage=" + inStorage +
//                ", sellQuantity=" + sellQuantity +
                ", description='" + description + '\'' +
                ", topic='" + topic + '\'' +
                ", origin='" + origin + '\'' +
                ", material='" + material + '\'' +
                ", image=" + image +
                ", id_ategory=" + id_category +
                ", id_brand=" + id_brand +
                ", id_recAge=" + id_recAge +
                '}';
    }
}

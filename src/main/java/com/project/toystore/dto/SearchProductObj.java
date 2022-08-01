package com.project.toystore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class SearchProductObj {
    private String categoryId;
    private String brandId;
    private String recAgeId;
    private String price;

    //sort by Price
    private String sortByPrice;
    private String[] keyWord;
    private String sort;
    //sort By category, brand, age
    private String category;
    private String brand;
    private String recAge;

    public SearchProductObj(){
        categoryId="";
        brandId="";
        recAgeId="";
        price="";
        sortByPrice="ASC";

    }

    @Override
    public String toString() {
        return "SearchProductObj{" +
                "categoryId='" + categoryId + '\'' +
                ", brandId='" + brandId + '\'' +
                ", recAgeId='" + recAgeId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

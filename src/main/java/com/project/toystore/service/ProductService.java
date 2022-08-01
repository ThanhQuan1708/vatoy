package com.project.toystore.service;

import com.project.toystore.dto.ProductDTO;
import com.project.toystore.dto.SearchProductObj;
import com.project.toystore.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface ProductService {
    Product save(ProductDTO product);
    Product update(ProductDTO product);
    void deleteById(long id);
    Product getProductById(long id);
    List<Product> getLastedProduct();
    List<Product> getAllProductByList(Set<Long> idList);

    Iterable<Product> getProductByCategory(String category);
    Iterable<Product> getProductByBrand(String brand);
    Iterable<Product> getProductByAge(String age);
    Iterable<Product> getProductByName(SearchProductObj obj);
    Page<Product> getAllProductByFilter(SearchProductObj obj, int page, int limit);
    Page<Product> getProductByName(SearchProductObj obj, int page, int size);
    Page<Product> getProductByName(String name, int page, int size );
    Page<Product> getProductByBrand(SearchProductObj obj, int page, int size );
}

package com.project.toystore.reposities;

import com.project.toystore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Set;

public interface ProductReposity extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    List<Product> findByIdIn(Set<Long> idList);
    List<Product> findFirst12ByCategoryNameContainingIgnoreCaseOrderByIdDesc(String cateName);

}

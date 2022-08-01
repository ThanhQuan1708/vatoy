package com.project.toystore.reposities;

import com.project.toystore.entities.Brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BrandReposity extends JpaRepository<Brand, Long>{
}

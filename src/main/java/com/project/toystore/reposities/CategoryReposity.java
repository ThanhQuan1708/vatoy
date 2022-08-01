package com.project.toystore.reposities;

import com.project.toystore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CategoryReposity extends JpaRepository<Category,Long> {

}

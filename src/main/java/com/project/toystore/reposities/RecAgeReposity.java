package com.project.toystore.reposities;

import com.project.toystore.entities.RecAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RecAgeReposity extends JpaRepository<RecAge, Long>{
}

package com.project.toystore.reposities;

import com.project.toystore.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ContactReposity extends JpaRepository<Contact,Long>,QuerydslPredicateExecutor<Contact> {
    int countByStatus(String status);
}

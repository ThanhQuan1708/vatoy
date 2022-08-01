package com.project.toystore.service;

import com.project.toystore.dto.SearchContactObj;
import com.project.toystore.entities.Contact;
import org.springframework.data.domain.Page;

import java.text.ParseException;

public interface ContactService {
    Contact findById(long id);
    Contact save(Contact contact);
    int countByStatus(String status);
    Page<Contact> getContactByFilter(SearchContactObj obj, int page) throws ParseException;
}

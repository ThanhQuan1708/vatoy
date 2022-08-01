package com.project.toystore.service.impl;

import com.project.toystore.dto.SearchContactObj;
import com.project.toystore.entities.Contact;
import com.project.toystore.entities.QContact;
import com.project.toystore.reposities.ContactReposity;
import com.project.toystore.service.ContactService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
@ComponentScan
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactReposity contactRepo;

    @Override
    public Contact findById(long id) {
        return contactRepo.findById(id).get();
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public int countByStatus(String status) {
        return contactRepo.countByStatus(status);
    }

    @Override
    public Page<Contact> getContactByFilter(SearchContactObj obj, int page) throws ParseException {
        BooleanBuilder builder = new BooleanBuilder();
        String status = obj.getStatus();
        String from = obj.getFrom();
        String to = obj.getTo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if(!status.equals("")){
            builder.and(QContact.contact.status.eq(status));
        }
        if(!from.equals("")&&from != null){
            builder.and(QContact.contact.dateCreate.goe(dateFormat.parse(from)));
        }
        if(!to.equals("")&&to != null){
            builder.and(QContact.contact.dateCreate.loe(dateFormat.parse(from)));
        }
        return contactRepo.findAll(builder, PageRequest.of(page-1,2));
    }
}

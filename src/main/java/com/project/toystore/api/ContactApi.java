package com.project.toystore.api;

import com.project.toystore.dto.ContactDTO;
import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.dto.SearchContactObj;
import com.project.toystore.entities.Contact;
import com.project.toystore.mail.Mail;
import com.project.toystore.service.ContactService;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contact")
public class ContactApi {
    @Autowired
    private UserService userService;
    @Autowired
    private Mail mail;
    @Autowired
    private ContactService contactService;
    @GetMapping("/all")
    public Page<Contact> getContactByFilter(@RequestParam(defaultValue = "1") int page,
                                            @RequestParam String status, @RequestParam String fromDate, @RequestParam String toDate) throws ParseException {
        SearchContactObj obj = new SearchContactObj();
        obj.setTo(toDate);
        obj.setFrom(fromDate);
        obj.setStatus(status);
        Page<Contact> listContact = contactService.getContactByFilter(obj,page);
        return listContact;
    }
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable long id){
        return contactService.findById(id);
    }
    @PostMapping("/reply")
    public ObjectReponse reply(@RequestBody @Valid ContactDTO dto, BindingResult result){
        ObjectReponse obj = new ObjectReponse();
        if(result.hasErrors()){
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            obj.setErrorMassage(errors);
            obj.setStatus("fail");
        }else{
            mail.sendMail(dto.getEmail(), dto.getTitle(), dto.getContactContent());
            Contact contact = contactService.findById(dto.getId());
            contact.setStatus("Đã trả lời");
            contact.setDateReply(new Date());
            contact.setReplyContent(dto.getContactContent());
            contactService.save(contact);
            obj.setStatus("success");

        }
        return obj;
    }

}

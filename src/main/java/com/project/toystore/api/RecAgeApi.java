package com.project.toystore.api;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.entities.Brand;
import com.project.toystore.entities.RecAge;
import com.project.toystore.service.BrandService;
import com.project.toystore.service.RecAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rec-age")
public class RecAgeApi {

    @Autowired
    private RecAgeService recAgeService;

    @GetMapping("/all")
    public Page<RecAge> getAllRecAge(@RequestParam(defaultValue = "1") int page) {
        return recAgeService.getAllRecAges(page-1,6);
    }

    @GetMapping("/{id}")
    public RecAge getById(@PathVariable long id) {
        return recAgeService.getRecAgeById(id);
    }

    @PostMapping(value = "/save")
    public ObjectReponse addRecAge(@RequestBody @Valid RecAge recAge, BindingResult result) {

        ObjectReponse obj = new ObjectReponse();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            obj.setErrorMassage(errors);
            obj.setStatus("fail");
        } else {
            recAgeService.save(recAge);
            obj.setData(recAge);
            obj.setStatus("success");
        }
        return obj;
    }

    @PutMapping(value = "/update")
    public ObjectReponse update(@RequestBody @Valid RecAge newInfo, BindingResult result) {

        ObjectReponse obj = new ObjectReponse();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            obj.setErrorMassage(errors);
            obj.setStatus("fail");
            errors = null;
        } else {
            recAgeService.update(newInfo);
            obj.setData(newInfo);
            obj.setStatus("success");
        }

        return obj;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHangSanXuat(@PathVariable long id) {
        recAgeService.delete(id);
        return "OK !";
    }
}

package com.project.toystore.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.entities.Category;
import com.project.toystore.service.CategoryService;
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
@RequestMapping("/api/category")
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public Page<Category> getAllDanhMuc(@RequestParam(defaultValue = "1") int page) {
        return categoryService.getAllCategories(page-1,6);
    }

    @GetMapping("/allForReal")
    public List<Category> getRealAllDanhMuc() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Category getDanhMucById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping(value = "/save")
    public ObjectReponse addDanhMuc(@RequestBody @Valid Category category, BindingResult result) {

        ObjectReponse obj = new ObjectReponse();

        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            obj.setErrorMassage(errors);

            List<String> keys = new ArrayList<String>(errors.keySet());
            for (String key: keys) {
                System.out.println(key + ": " + errors.get(key));
            }

            obj.setStatus("fail");
            errors = null;
            ;
        } else {
            categoryService.save(category);
            obj.setData(category);
            obj.setStatus("success");
        }
        return obj;
    }

    @PutMapping(value = "/update")
    public ObjectReponse updateDanhMuc(@RequestBody @Valid Category editDanhMuc, BindingResult result, HttpServletRequest request) {

        ObjectReponse obj = new ObjectReponse();
        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            obj.setErrorMassage(errors);
            obj.setStatus("fail");

            errors = null;

        } else {
            categoryService.update(editDanhMuc);
            obj.setData(editDanhMuc);
            obj.setStatus("success");
        }

        return obj;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDanhMuc(@PathVariable long id, HttpServletRequest request) {
        categoryService.delete(id);
        request.getSession().setAttribute("listDanhMuc", categoryService.getAllCategory());
        return "OK !";
    }

}

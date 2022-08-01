package com.project.toystore.api;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.entities.Brand;
import com.project.toystore.service.BrandService;
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
@RequestMapping("/api/brand")
public class BrandApi {

    @Autowired
    private BrandService brandService;

    @GetMapping("/all")
    public Page<Brand> getAllBrands(@RequestParam(defaultValue = "1") int page) {
        return brandService.getAllBrands(page-1,6);
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable long id) {
        return brandService.getById(id);
    }

    @PostMapping(value = "/save")
    public ObjectReponse addBrand(@RequestBody @Valid Brand brand, BindingResult result) {

        ObjectReponse obj = new ObjectReponse();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            obj.setErrorMassage(errors);
            obj.setStatus("fail");
        } else {
            brandService.save(brand);
            obj.setData(brand);
            obj.setStatus("success");
        }
        return obj;
    }

    @PutMapping(value = "/update")
    public ObjectReponse updateBrand(@RequestBody @Valid Brand newInfo, BindingResult result) {

        ObjectReponse obj = new ObjectReponse();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            obj.setErrorMassage(errors);
            obj.setStatus("fail");
            errors = null;
        } else {
            brandService.update(newInfo);
            obj.setData(newInfo);
            obj.setStatus("success");
        }

        return obj;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHangSanXuat(@PathVariable long id) {
        brandService.delete(id);
        return "OK !";
    }
}

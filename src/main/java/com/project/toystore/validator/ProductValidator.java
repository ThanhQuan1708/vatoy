package com.project.toystore.validator;

import com.project.toystore.dto.ProductDTO;
import com.project.toystore.entities.Product;
import com.project.toystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz){return ProductDTO.class.isAssignableFrom(clazz);}

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;
        ValidationUtils.rejectIfEmpty(errors,"name","error.name","This field is not null");
        ValidationUtils.rejectIfEmpty(errors,"price","error.price","This field is not null");
        ValidationUtils.rejectIfEmpty(errors,"inStorage","error.inStorage","This field is not null");
        ValidationUtils.rejectIfEmpty(errors,"description","error.description","This field is not null");
        ValidationUtils.rejectIfEmpty(errors,"topic","error.topic","This field is not null");
        ValidationUtils.rejectIfEmpty(errors,"origin","error.origin","This field is not null");
        ValidationUtils.rejectIfEmpty(errors,"material","error.material","This field is not null");
        if(Integer.parseInt(productDTO.getPrice()) < 0) {
            errors.rejectValue("price", "error.price", "Price can't lower than 0");
        }

        if(Integer.parseInt(productDTO.getInStorage()) < 0) {
            errors.rejectValue("inStorage", "error.inStorage", "Quantity can't lower than 0");
        }
    }

}

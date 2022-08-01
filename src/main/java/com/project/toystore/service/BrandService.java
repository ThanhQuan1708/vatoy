package com.project.toystore.service;

import com.project.toystore.entities.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrand();
    Brand save(Brand brand);
    Brand update(Brand brand);
    void delete(long id);
    Brand getById(long id);
    Page<Brand> getAllBrands(int page, int size);

}

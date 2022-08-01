package com.project.toystore.service.impl;

import com.project.toystore.entities.Brand;
import com.project.toystore.reposities.BrandReposity;
import com.project.toystore.reposities.UserReposity;
import com.project.toystore.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@ComponentScan
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private UserReposity userReposity;
    @Autowired
    private BrandReposity brandRepo;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepo.findAll();
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepo.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        return brandRepo.save(brand);
    }

    @Override
    public void delete(long id) {
        brandRepo.deleteById(id);
    }

    @Override
    public Brand getById(long id) {
        return brandRepo.findById(id).get();
    }

    @Override
    public Page<Brand> getAllBrands(int page, int size) {
        return brandRepo.findAll(PageRequest.of(page, size));
    }
}

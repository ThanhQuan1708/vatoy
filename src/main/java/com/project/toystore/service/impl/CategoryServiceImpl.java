package com.project.toystore.service.impl;

import com.project.toystore.entities.Category;
import com.project.toystore.reposities.CategoryReposity;
import com.project.toystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@ComponentScan
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryReposity categoryRepo;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Page<Category> getAllCategories(int page, int size) {
        return categoryRepo.findAll(PageRequest.of(page, size));
    }
}

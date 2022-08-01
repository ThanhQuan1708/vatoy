package com.project.toystore.service;

import com.project.toystore.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(long id);
    Category save(Category category);
    Category update(Category category);
    void delete(long id);
    Page<Category> getAllCategories(int page, int size);
}

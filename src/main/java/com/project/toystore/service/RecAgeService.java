package com.project.toystore.service;

import com.project.toystore.entities.RecAge;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecAgeService {
    RecAge save(RecAge recAge);
    RecAge update(RecAge recAge);
    void delete(long id);
    List<RecAge> getAllRecAge();
    RecAge getRecAgeById(long id);
    Page<RecAge> getAllRecAges(int page, int size);
}

package com.project.toystore.service.impl;

import com.project.toystore.entities.RecAge;
import com.project.toystore.reposities.RecAgeReposity;
import com.project.toystore.service.RecAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@ComponentScan
@Service
public class RecAgeServiceImpl implements RecAgeService {
    @Autowired
    private  RecAgeReposity recAgeRepo;

    @Override
    public RecAge save(RecAge recAge) {
        return recAgeRepo.save(recAge);
    }

    @Override
    public RecAge update(RecAge recAge) {
         return recAgeRepo.save(recAge);
    }

    @Override
    public void delete(long id) {
        recAgeRepo.deleteById(id);
    }

    @Override
    public List<RecAge> getAllRecAge() {
        return recAgeRepo.findAll();
    }

    @Override
    public RecAge getRecAgeById(long id) {
        return recAgeRepo.findById(id).get();
    }

    @Override
    public Page<RecAge> getAllRecAges(int page, int size) {
        return recAgeRepo.findAll(PageRequest.of(page,size));
    }
}

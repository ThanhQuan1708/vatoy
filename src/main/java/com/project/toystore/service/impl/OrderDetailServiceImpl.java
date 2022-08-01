package com.project.toystore.service.impl;

import com.project.toystore.entities.OrderDetail;
import com.project.toystore.reposities.OrderDetailReposity;
import com.project.toystore.reposities.OrderReposity;
import com.project.toystore.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailReposity orderDetailReposity;

    @Override
    public List<OrderDetail> save(List<OrderDetail> list) {
        return orderDetailReposity.saveAll(list);
    }
}

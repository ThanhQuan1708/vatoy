package com.project.toystore.service;

import com.project.toystore.entities.OrderDetail;

import java.util.List;

public  interface OrderDetailService {
    List<OrderDetail> save(List<OrderDetail> list);
}

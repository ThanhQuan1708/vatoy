package com.project.toystore.service.impl;

import com.project.toystore.dto.SearchOrderObj;
import com.project.toystore.entities.Orderr;
import com.project.toystore.entities.QOrderr;
import com.project.toystore.entities.User;
import com.project.toystore.reposities.OrderReposity;
import com.project.toystore.service.OrderService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
@ComponentScan
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderReposity orderRepo;
    @Override
    public Page<Orderr> findOrderByShipper(SearchOrderObj obj, int page, int size, User shipper) throws Exception {
        BooleanBuilder builder = new BooleanBuilder();
        String status = obj.getStatus();
        String from = obj.getFrom();
        String to = obj.getTo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        builder.and(QOrderr.orderr.shipper.eq(shipper));
        if(!status.equals("")){
            builder.and(QOrderr.orderr.status.eq(status));
        }
        if (!from.equals("")&&from != null){
            if(status.equals("Đang giao")){
                builder.and(QOrderr.orderr.deliveryDate.goe(dateFormat.parse(from)));
            }else{
                builder.and(QOrderr.orderr.reciveDate.goe(dateFormat.parse(from)));
            }
        }
        if (!to.equals("")&&to != null){
            if(status.equals("Đang giao")){
                builder.and(QOrderr.orderr.deliveryDate.loe(dateFormat.parse(to)));
            }else{
                builder.and(QOrderr.orderr.reciveDate.loe(dateFormat.parse(to)));
            }
        }
        return orderRepo.findAll(builder, PageRequest.of(page-1,size));
    }

    @Override
    public Page<Orderr> getAllOrderByFilter(SearchOrderObj obj, int page) throws Exception {
        BooleanBuilder builder = new BooleanBuilder();

        String trangThaiDon = obj.getStatus();
        String tuNgay = obj.getFrom();
        String denNgay = obj.getTo();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

        if (!trangThaiDon.equals("")) {
            builder.and(QOrderr.orderr.status.eq(trangThaiDon));
        }

        if (!tuNgay.equals("") && tuNgay != null) {
            if (trangThaiDon.equals("") || trangThaiDon.equals("Đang chờ giao") || trangThaiDon.equals("Đã hủy")) {
                builder.and(QOrderr.orderr.orderDate.goe(formatDate.parse(tuNgay)));
            } else if (trangThaiDon.equals("Đang giao")) {
                builder.and(QOrderr.orderr.deliveryDate.goe(formatDate.parse(tuNgay)));
            } else { // hoàn thành
                builder.and(QOrderr.orderr.reciveDate.goe(formatDate.parse(tuNgay)));
            }
        }

        if (!denNgay.equals("") && denNgay != null) {
            if (trangThaiDon.equals("") || trangThaiDon.equals("Đang chờ giao") || trangThaiDon.equals("Đã hủy")) {
                builder.and(QOrderr.orderr.orderDate.loe(formatDate.parse(denNgay)));
            } else if (trangThaiDon.equals("Đang giao")) {
                builder.and(QOrderr.orderr.deliveryDate.loe(formatDate.parse(denNgay)));
            } else { // hoàn thành
                builder.and(QOrderr.orderr.reciveDate.loe(formatDate.parse(denNgay)));
            }
        }

        return orderRepo.findAll(builder,PageRequest.of(page-1, 6));
    }

    @Override
    public Orderr update(Orderr order) {
        return orderRepo.save(order);
    }

    @Override
    public Orderr findById(long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public Orderr save(Orderr order) {
        return orderRepo.save(order);
    }

//    @Override
//    public List<Object> getOrderByMonthAndYear() {
//        return null;
//    }

    @Override
    public List<Object> getOrderByMonthAndYear() {
        return orderRepo.getOrderByMonthAndYear();
    }

    @Override
    public List<Orderr> findByStatusAndShipper(String str, User shipper) {
        return orderRepo.findByStatusAndShipper(str,shipper);
    }

    @Override
    public List<Orderr> getOrderByUser(User user) {
        return null;
    }

    @Override
    public int countByStatus(String status) {
        return orderRepo.countByStatus(status);
    }
}

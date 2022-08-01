package com.project.toystore.service;

import com.project.toystore.dto.SearchOrderObj;
import com.project.toystore.entities.Orderr;
import com.project.toystore.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    Page<Orderr> getAllOrderByFilter(SearchOrderObj obj, int page) throws Exception;
    Page<Orderr> findOrderByShipper(SearchOrderObj obj, int page, int size, User shipper) throws Exception;

    Orderr update(Orderr order);
    Orderr findById(long id);
    Orderr save(Orderr order);

    List<Object> getOrderByMonthAndYear();
    List<Orderr> findByStatusAndShipper(String str, User shipper);
    List<Orderr> getOrderByUser (User user);
    int countByStatus(String status);
}


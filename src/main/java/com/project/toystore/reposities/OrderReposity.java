package com.project.toystore.reposities;

import com.project.toystore.entities.Orderr;
import com.project.toystore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrderReposity extends JpaRepository<Orderr,Long>, QuerydslPredicateExecutor<Orderr> {
    @Query(value = "select DATE_FORMAT(o.reciveDate,'%m') as month, " +
            "DATE_FORMAT(o.reciveDate,'%Y') as year," +
            "sum(dt.receiveQuantity*dt.price) as total " +
            "from Orderr o , OrderDetail  dt " +
            "where o.id = dt.order.id and o.status='Hoàn thành' " +
            "group by DATE_FORMAT(o.reciveDate,'%Y%m') " +
            "order by year asc")
    public List<Object> getOrderByMonthAndYear();
    public List<Orderr> findByStatusAndShipper(String status, User shipper);
    public List<Orderr> findByCustomer(User user);
    public int countByStatus(String status);

}

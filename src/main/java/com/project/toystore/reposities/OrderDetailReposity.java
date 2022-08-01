package com.project.toystore.reposities;

import com.project.toystore.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailReposity  extends JpaRepository<OrderDetail, Long> {
}

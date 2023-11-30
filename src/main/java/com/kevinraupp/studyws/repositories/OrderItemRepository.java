package com.kevinraupp.studyws.repositories;

import com.kevinraupp.studyws.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}

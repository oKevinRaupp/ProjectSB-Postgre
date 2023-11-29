package com.kevinraupp.studyws.repositories;

import com.kevinraupp.studyws.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

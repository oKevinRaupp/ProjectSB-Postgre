package com.kevinraupp.studyws.services;

import com.kevinraupp.studyws.entities.Order;
import com.kevinraupp.studyws.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    private Logger logger = Logger.getLogger(CategoryService.class.getName());

    public List<Order> finall() {
        logger.info("Finding all orders!");
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        logger.info("Finding one order!");
        return orderRepository.findById(id);
    }
}
